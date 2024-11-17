@file:Suppress("ktlint:standard:filename", "MatchingDeclarationName")

package momosetkn.komapper

import org.komapper.core.dsl.expression.SortExpression
import org.komapper.core.dsl.metamodel.EntityMetamodel
import org.komapper.core.dsl.metamodel.PropertyMetamodel
import org.komapper.core.dsl.operator.asc
import org.komapper.core.dsl.operator.desc
import org.komapper.core.dsl.query.EntitySelectQuery
import org.komapper.core.dsl.query.SelectQueryBuilder

class DomaLikeOrderByDsl {
    var orderByColumns = mutableListOf<SortExpression>()
    fun <
        ENTITY : Any,
        EXTERIOR : Any,
        INTERIOR : Any
    > asc(propertyMetamodel: PropertyMetamodel<ENTITY, EXTERIOR, INTERIOR>) {
        orderByColumns += propertyMetamodel.asc()
    }

    fun <
        ENTITY : Any,
        EXTERIOR : Any,
        INTERIOR : Any
    > desc(propertyMetamodel: PropertyMetamodel<ENTITY, EXTERIOR, INTERIOR>) {
        orderByColumns += propertyMetamodel.desc()
    }
}

fun <
    ENTITY : Any,
    ID : Any,
    META : EntityMetamodel<ENTITY, ID, META>
> SelectQueryBuilder<ENTITY, ID, META>.orderBy(
    block: DomaLikeOrderByDsl.() -> Unit
): EntitySelectQuery<ENTITY> {
    val dsl = DomaLikeOrderByDsl()

    block(dsl)
    return orderBy(dsl.orderByColumns)
}
