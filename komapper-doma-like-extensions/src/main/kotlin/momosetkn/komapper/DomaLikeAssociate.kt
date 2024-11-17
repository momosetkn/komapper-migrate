package momosetkn.komapper

import org.komapper.core.dsl.context.SelectContext
import org.komapper.core.dsl.metamodel.EntityMetamodel
import org.komapper.core.dsl.query.EntitySelectQuery
import org.komapper.core.dsl.query.EntityStore
import org.komapper.jdbc.JdbcDatabase
import kotlin.collections.get
import kotlin.reflect.jvm.isAccessible

fun main() {
    val entityStoreClass = Class.forName("org.komapper.core.dsl.query.EntityStoreImpl")
    val entitySetsCallable = entityStoreClass.kotlin.members.find { it.name == "entitySets" }
    checkNotNull(entitySetsCallable)
    println(entityStoreClass)
}

val entitySetsCallable = run {
    val clazz = Class.forName("org.komapper.core.dsl.query.EntityStoreImpl")
    val prop = clazz.kotlin.members.find { it.name == "entitySets" }
    checkNotNull(prop)
    prop.also {
        it.isAccessible = true
    }
}

fun getEntitySets(entityStore: EntityStore): Map<EntityMetamodel<*, *, *>, Set<Any>> {
    return entitySetsCallable.call(entityStore) as Map<EntityMetamodel<*, *, *>, Set<Any>>
}

data class AssociateDslContext(
    val entityMetaLeft: EntityMetamodel<out Any, *, *>,
    val entityMetaRight: EntityMetamodel<out Any, *, *>,
    val associateBlock: ((Any, Any) -> Unit)?,
    val associateWithBlock: ((Any, Any) -> Any)?,
)

class AssociateDsl {
    internal val mutableContextList = mutableListOf<AssociateDslContext>()
    fun <LEFT : Any, RIGHT : Any> associate(
        entityMetaLeft: EntityMetamodel<LEFT, *, *>,
        entityMetaRight: EntityMetamodel<RIGHT, *, *>,
        block: (LEFT, RIGHT) -> Unit,
    ) {
        mutableContextList += AssociateDslContext(
            entityMetaLeft = entityMetaLeft,
            entityMetaRight = entityMetaRight,
            associateBlock = block as (Any, Any) -> Unit,
            associateWithBlock = null,
        )
    }

    fun <LEFT : Any, RIGHT : Any> associateWith(
        entityMetaLeft: EntityMetamodel<LEFT, *, *>,
        entityMetaRight: EntityMetamodel<RIGHT, *, *>,
        block: (LEFT, RIGHT) -> LEFT,
    ) {
        mutableContextList += AssociateDslContext(
            entityMetaLeft = entityMetaLeft,
            entityMetaRight = entityMetaRight,
            associateBlock = null,
            associateWithBlock = block as ((Any, Any) -> Any)?,
        )
    }
}

fun <T : Any> JdbcDatabase.runQueryWithAssociate(
    query: EntitySelectQuery<T>,
    block: AssociateDsl.() -> Unit,
): List<T> {
    val entityStore = this.runQuery(query.includeAll())
    val entitySets = getEntitySets(entityStore)
    val entityMetas = entitySets.map { it.key }.toSet()

    val dsl = AssociateDsl()
    block(dsl)
    // Process the deeper ones first.
    val entityMetaAssociateMap =
        LinkedHashMap<EntityMetamodel<out Any, *, *>, MutableList<EntityMetamodel<out Any, *, *>>>()
    dsl.mutableContextList.forEach { context ->
        val mutableList = entityMetaAssociateMap.getOrPut(context.entityMetaLeft) {
            mutableListOf<EntityMetamodel<out Any, *, *>>()
        }
        mutableList += context.entityMetaRight
    }
    val sortedEntityMetas = entityMetas.sortedBy { entityMeta ->
        val start = entityMetaAssociateMap[entityMeta]
        start ?: return@sortedBy 0
        fun rec(currentEntityMeta: EntityMetamodel<out Any, *, *>?, currentDepth: Int = 0): Int {
            if (currentEntityMeta == null) return currentDepth
            val currentListValue = entityMetaAssociateMap[currentEntityMeta]
            return currentListValue?.maxOfOrNull { rec(it, currentDepth + 1) } ?: 0
        }
        start.maxOfOrNull { rec(it) } ?: 0
    }.reversed()
    // original entity to associated entity
    val associatedEntityMap = mutableMapOf<Any, Any>()
    val associatedEntitySets = entitySets.entries
        .sortedBy {
            sortedEntityMetas.indexOf(it.key)
        }.map { (entityMeta, items) ->
            val exampleInstance = items.firstOrNull()
            if (exampleInstance === null) return@map entityMeta to items

            entityMeta to associate(
                contextList = dsl.mutableContextList,
                entityStore = entityStore,
                entityMeta = entityMeta,
                allEntityMetas = entityMetas,
                items = items,
                associatedEntityMap = associatedEntityMap
            )
        }
    val rootEntityMeta = (query.context as SelectContext<*, *, *>).target
    val rootEntitySet = associatedEntitySets.find { (entityMeta, items) ->
        entityMeta == rootEntityMeta
    }
    checkNotNull(rootEntitySet)
    return rootEntitySet.second.toList() as List<T>
}

@Suppress("LongParameterList")
private fun associate(
    contextList: List<AssociateDslContext>,
    entityStore: EntityStore,
    entityMeta: EntityMetamodel<*, *, *>,
    allEntityMetas: Set<EntityMetamodel<*, *, *>>,
    items: Set<Any>,
    associatedEntityMap: MutableMap<Any, Any>
): List<Any> {
    val contexts = allEntityMetas.flatMap { otherEntityMeta ->
        val key = Pair(entityMeta, otherEntityMeta)
        contextList.filter {
            val contextKey = Pair(it.entityMetaLeft, it.entityMetaRight)
            contextKey == key
        }
    }
    return items.map { item ->
        var mutableItem = item
        contexts.forEach { context ->
            val map = entityStore.oneToMany(context.entityMetaLeft, context.entityMetaRight)
            val actual = map[item]
            actual?.forEach { a ->
                val associated = associatedEntityMap[a] ?: a
                when {
                    context.associateBlock != null ->
                        context.associateBlock(mutableItem, associated)

                    context.associateWithBlock != null -> {
                        mutableItem = context.associateWithBlock(mutableItem, associated)
                    }

                    else -> mutableItem
                }
            }
        }
        associatedEntityMap[item] = mutableItem
        mutableItem
    }
}
