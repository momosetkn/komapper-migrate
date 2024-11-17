@file:Suppress("ktlint:standard:filename")

package momosetkn.komapper

import org.komapper.annotation.KomapperAggregateRoot
import org.komapper.annotation.KomapperColumn
import org.komapper.annotation.KomapperEntity
import org.komapper.annotation.KomapperExperimentalAssociation
import org.komapper.annotation.KomapperId
import org.komapper.annotation.KomapperOneToMany
import org.komapper.annotation.KomapperOneToOne
import org.komapper.annotation.KomapperTable

@KomapperEntity
@KomapperTable(name = "follower_groups")
data class FollowerGroups(
    @KomapperId @KomapperColumn(name = "user_group_id") val userGroupId: Int,
    @KomapperId @KomapperColumn(name = "project_id") val projectId: Int,
) {
    var userGroup: UserGroups? = null
}

@KomapperEntity
@KomapperTable(name = "followers")
data class Followers(
    @KomapperId @KomapperColumn(name = "user_id") val userId: Int,
    @KomapperId @KomapperColumn(name = "project_id") val projectId: Int,
) {
    var user: Users? = null
}

@OptIn(KomapperExperimentalAssociation::class)
@KomapperEntity
@KomapperTable(name = "projects")
@KomapperAggregateRoot
@KomapperOneToOne(Tasks::class)
@KomapperOneToMany(Followers::class)
@KomapperOneToMany(FollowerGroups::class)
data class Projects(
    @KomapperId @KomapperColumn(name = "id") val id: Int,
    @KomapperId @KomapperColumn(name = "task_id") val taskId: Int,
    @KomapperColumn(name = "name") val name: String,
) {
    var task: Tasks? = null
    var followersList: List<Followers> = ArrayList()
    var followerGroupsList: List<FollowerGroups> = ArrayList()
}

@KomapperEntity
@KomapperTable(name = "tasks")
data class Tasks(
    @KomapperId @KomapperColumn(name = "id") val id: Int,
    @KomapperColumn(name = "name") val name: String,
)

@KomapperEntity
@KomapperTable(name = "user_groups")
data class UserGroups(
    @KomapperId @KomapperColumn(name = "id") val id: Int,
    @KomapperColumn(name = "name") val name: String,
)

@KomapperEntity
@KomapperTable(name = "users")
data class Users(
    @KomapperId @KomapperColumn(name = "id") val id: Int,
    @KomapperColumn(name = "email") val email: String,
    @KomapperColumn(name = "name") val name: String,
)
