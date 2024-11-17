package momosetkn.komapper

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.komapper.core.dsl.Meta
import org.komapper.core.dsl.QueryDsl
import org.komapper.jdbc.JdbcDatabase
import org.komapper.jdbc.JdbcDialects
import javax.sql.DataSource

class AssociateSpec : FunSpec({
    val db = JdbcDatabase(createHikariDataSource(), JdbcDialects.get("postgresql"))

    val metaProjects = Meta.projects
    val metaTasks = Meta.tasks
    val metaFollowers = Meta.followers
    val metaFollowerGroups = Meta.followerGroups
    val metaUserGroups = Meta.userGroups
    val metaUsers = Meta.users

    val users = listOf(
        Users(
            id = 1,
            email = "email1",
            name = "user1",
        ),
        Users(
            id = 2,
            email = "email2",
            name = "user2",
        ),
    )
    val userGroups = listOf(
        UserGroups(
            id = 1,
            name = "userGroup1",
        ),
        UserGroups(
            id = 2,
            name = "userGroup2",
        ),
    )
    val tasks = listOf(
        Tasks(
            id = 1,
            name = "task1",
        ),
        Tasks(
            id = 2,
            name = "task2",
        ),
    )
    val projects = listOf(
        Projects(
            id = 1,
            taskId = tasks[0].id,
            name = "project1",
        ),
        Projects(
            id = 2,
            taskId = tasks[1].id,
            name = "project2",
        ),
    )

    val followers = listOf(
        Followers(
            userId = users[0].id,
            projectId = projects[0].id,
        ),
        Followers(
            userId = users[1].id,
            projectId = projects[1].id,
        ),
    )
    val followerGroups = listOf(
        FollowerGroups(
            userGroupId = userGroups[0].id,
            projectId = projects[0].id,
        ),
        FollowerGroups(
            userGroupId = userGroups[1].id,
            projectId = projects[1].id,
        ),
    )
    beforeSpec {
        db.withTransaction {
            db.runQuery(
                QueryDsl.create(
                    metaUsers,
                    metaUserGroups,
                    metaTasks,
                    metaProjects,
                    metaFollowers,
                    metaFollowerGroups,
                )
            )
            db.runQuery(QueryDsl.insert(metaUsers).batch(users))
            db.runQuery(QueryDsl.insert(metaUserGroups).batch(userGroups))
            db.runQuery(QueryDsl.insert(metaTasks).batch(tasks))
            db.runQuery(QueryDsl.insert(metaProjects).batch(projects))
            db.runQuery(QueryDsl.insert(metaFollowers).batch(followers))
            db.runQuery(QueryDsl.insert(metaFollowerGroups).batch(followerGroups))
        }
    }

    context("associate & associateWith query") {
        fun findList(): List<Projects> {
            val query = QueryDsl
                .from(metaProjects)
                .leftJoin(metaTasks) {
                    metaProjects.taskId eq metaTasks.id
                }
                .leftJoin(metaFollowers) {
                    metaProjects.id eq metaFollowers.projectId
                }
                .leftJoin(metaUsers) {
                    metaFollowers.userId eq metaUsers.id
                }
                .leftJoin(metaFollowerGroups) {
                    metaProjects.id eq metaFollowerGroups.projectId
                }
                .leftJoin(metaUserGroups) {
                    metaFollowerGroups.userGroupId eq metaUserGroups.id
                }
                .limit(100)

            val items = db.runQueryWithAssociate(query) {
                associate(metaProjects, metaTasks) { l, r ->
                    l.task = r
                }
                associate(metaProjects, metaFollowers) { l, r ->
                    l.followersList += r
                }
                associate(metaFollowers, metaUsers) { l, r ->
                    l.user = r
                }
                associateWith(metaProjects, metaFollowerGroups) { l, r ->
                    l.copy().apply {
                        task = l.task
                        followersList = l.followersList
                        followerGroupsList = l.followerGroupsList + r
                    }
                }
                associateWith(metaFollowerGroups, metaUserGroups) { l, r ->
                    l.copy().apply {
                        userGroup = r
                    }
                }
            }
            return items
        }
        test("can get associated entities") {
            val result = findList()

            result.size shouldBe 2
            result shouldContainExactlyInAnyOrder projects
            result.flatMap { it.followersList } shouldContainExactlyInAnyOrder followers
            result.flatMap { it.followerGroupsList } shouldContainExactlyInAnyOrder followerGroups
            result.flatMap { r -> r.followersList.map { it.user } } shouldContainExactlyInAnyOrder users
            result.flatMap { r -> r.followerGroupsList.map { it.userGroup } } shouldContainExactlyInAnyOrder userGroups
            result.map { it.task } shouldContainExactlyInAnyOrder tasks
        }
    }
})

private fun createHikariDataSource(): DataSource {
    val hikariConfig = HikariConfig().apply {
        jdbcUrl = "jdbc:tc:postgis:12-3.4:///test?TC_DAEMON=true"
        username = "test"
        password = "test"
        maximumPoolSize = 1
        isAutoCommit = false
    }
    val hikariDataSource = HikariDataSource(hikariConfig)
    return hikariDataSource
}
