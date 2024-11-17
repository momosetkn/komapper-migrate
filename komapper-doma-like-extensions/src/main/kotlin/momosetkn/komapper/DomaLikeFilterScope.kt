@file:Suppress("ktlint:standard:filename", "MatchingDeclarationName")

package momosetkn.komapper

import org.komapper.core.dsl.expression.ColumnExpression
import org.komapper.core.dsl.expression.CompositeColumnExpression
import org.komapper.core.dsl.expression.InteriorExpression
import org.komapper.core.dsl.expression.SubqueryExpression
import org.komapper.core.dsl.scope.FilterScope

private typealias FilterScopeAll = FilterScope<*>

@Deprecated("migrate to original method", ReplaceWith("left eq operand"))
fun <T : Any, S : Any> FilterScopeAll.eq(
    left: ColumnExpression<T, S>,
    operand: ColumnExpression<T, S>,
) {
    left eq operand
}

@Deprecated("migrate to original method", ReplaceWith("left eq operand"))
fun <T : Any, S : Any> FilterScopeAll.eq(
    left: ColumnExpression<T, S>,
    operand: T?,
) {
    left eq operand
}

@Deprecated("migrate to original method", ReplaceWith("left eq operand"))
fun <T : Any, S : Any> FilterScopeAll.eq(
    left: T?,
    operand: ColumnExpression<T, S>,
) {
    left eq operand
}

@Deprecated("migrate to original method", ReplaceWith("left eq operand"))
fun <T : Any> FilterScopeAll.eq(
    left: CompositeColumnExpression<T>,
    operand: T?,
) {
    left eq operand
}

@Deprecated("migrate to original method", ReplaceWith("left eq operand"))
fun <T : Any> FilterScopeAll.eq(
    left: T?,
    operand: CompositeColumnExpression<T>,
) {
    left eq operand
}

@Deprecated("migrate to original method", ReplaceWith("left eq operand"))
fun FilterScopeAll.eq(
    left: InteriorExpression<Number>,
    operand: InteriorExpression<Number>,
) {
    left eq operand
}

@Deprecated("migrate to original method", ReplaceWith("left notEq operand"))
fun <T : Any, S : Any> FilterScopeAll.ne(
    left: ColumnExpression<T, S>,
    operand: ColumnExpression<T, S>,
) {
    left notEq operand
}

@Deprecated("migrate to original method", ReplaceWith("left notEq operand"))
fun <T : Any, S : Any> FilterScopeAll.ne(
    left: ColumnExpression<T, S>,
    operand: T?,
) {
    left notEq operand
}

@Deprecated("migrate to original method", ReplaceWith("left notEq operand"))
fun <T : Any, S : Any> FilterScopeAll.ne(
    left: T?,
    operand: ColumnExpression<T, S>,
) {
    left notEq operand
}

@Deprecated("migrate to original method", ReplaceWith("left notEq operand"))
fun FilterScopeAll.ne(
    left: InteriorExpression<Number>,
    operand: InteriorExpression<Number>,
) {
    left notEq operand
}

@Deprecated("migrate to original method", ReplaceWith("left less operand"))
fun <T : Any, S : Any> FilterScopeAll.lt(
    left: ColumnExpression<T, S>,
    operand: ColumnExpression<T, S>,
) {
    left less operand
}

@Deprecated("migrate to original method", ReplaceWith("left less operand"))
fun <T : Any, S : Any> FilterScopeAll.lt(
    left: ColumnExpression<T, S>,
    operand: T?,
) {
    left less operand
}

@Deprecated("migrate to original method", ReplaceWith("left less operand"))
fun <T : Any, S : Any> FilterScopeAll.lt(
    left: T?,
    operand: ColumnExpression<T, S>,
) {
    left less operand
}

@Deprecated("migrate to original method", ReplaceWith("left less operand"))
fun FilterScopeAll.lt(
    left: InteriorExpression<Number>,
    operand: InteriorExpression<Number>,
) {
    left less operand
}

@Deprecated("migrate to original method", ReplaceWith("left lessEq operand"))
fun <T : Any, S : Any> FilterScopeAll.le(
    left: ColumnExpression<T, S>,
    operand: ColumnExpression<T, S>,
) {
    left lessEq operand
}

@Deprecated("migrate to original method", ReplaceWith("left lessEq operand"))
fun <T : Any, S : Any> FilterScopeAll.le(
    left: ColumnExpression<T, S>,
    operand: T?,
) {
    left lessEq operand
}

@Deprecated("migrate to original method", ReplaceWith("left lessEq operand"))
fun <T : Any, S : Any> FilterScopeAll.le(
    left: T?,
    operand: ColumnExpression<T, S>,
) {
    left lessEq operand
}

@Deprecated("migrate to original method", ReplaceWith("left lessEq operand"))
fun FilterScopeAll.le(
    left: InteriorExpression<Number>,
    operand: InteriorExpression<Number>,
) {
    left lessEq operand
}

@Deprecated("migrate to original method", ReplaceWith("left greater operand"))
fun <T : Any, S : Any> FilterScopeAll.gt(
    left: ColumnExpression<T, S>,
    operand: ColumnExpression<T, S>,
) {
    left greater operand
}

@Deprecated("migrate to original method", ReplaceWith("left greater operand"))
fun <T : Any, S : Any> FilterScopeAll.gt(
    left: ColumnExpression<T, S>,
    operand: T?,
) {
    left greater operand
}

@Deprecated("migrate to original method", ReplaceWith("left greater operand"))
fun <T : Any, S : Any> FilterScopeAll.gt(
    left: T?,
    operand: ColumnExpression<T, S>,
) {
    left greater operand
}

@Deprecated("migrate to original method", ReplaceWith("left greater operand"))
fun FilterScopeAll.gt(
    left: InteriorExpression<Number>,
    operand: InteriorExpression<Number>,
) {
    left greater operand
}

@Deprecated("migrate to original method", ReplaceWith("left greaterEq operand"))
fun <T : Any, S : Any> FilterScopeAll.ge(
    left: ColumnExpression<T, S>,
    operand: ColumnExpression<T, S>,
) {
    left greaterEq operand
}

@Deprecated("migrate to original method", ReplaceWith("left greaterEq operand"))
fun <T : Any, S : Any> FilterScopeAll.ge(
    left: ColumnExpression<T, S>,
    operand: T?,
) {
    left greaterEq operand
}

@Deprecated("migrate to original method", ReplaceWith("left greaterEq operand"))
fun <T : Any, S : Any> FilterScopeAll.ge(
    left: T?,
    operand: ColumnExpression<T, S>,
) {
    left greaterEq operand
}

@Deprecated("migrate to original method", ReplaceWith("left greaterEq operand"))
fun FilterScopeAll.ge(
    left: InteriorExpression<Number>,
    operand: InteriorExpression<Number>,
) {
    left greaterEq operand
}

@Deprecated("migrate to original method", ReplaceWith("left.isNull()"))
fun <T : Any, S : Any> FilterScopeAll.isNull(left: ColumnExpression<T, S>) {
    left.isNull()
}

@Deprecated("migrate to original method", ReplaceWith("left.isNotNull()"))
fun <T : Any, S : Any> FilterScopeAll.isNotNull(left: ColumnExpression<T, S>) {
    left.isNotNull()
}

@Deprecated("migrate to original method", ReplaceWith("left like operand"))
fun <T : Any, S : CharSequence> FilterScopeAll.like(
    left: ColumnExpression<T, S>,
    operand: CharSequence?,
) {
    left like operand
}

@Deprecated("migrate to original method", ReplaceWith("left notLike operand"))
fun <T : Any, S : CharSequence> FilterScopeAll.notLike(
    left: ColumnExpression<T, S>,
    operand: CharSequence?,
) {
    left notLike operand
}

@Deprecated("migrate to original method", ReplaceWith("left startsWith operand"))
fun <T : Any, S : CharSequence> FilterScopeAll.startsWith(
    left: ColumnExpression<T, S>,
    operand: CharSequence?,
) {
    left startsWith operand
}

@Deprecated("migrate to original method", ReplaceWith("left notStartsWith operand"))
fun <T : Any, S : CharSequence> FilterScopeAll.notStartsWith(
    left: ColumnExpression<T, S>,
    operand: CharSequence?,
) {
    left notStartsWith operand
}

@Deprecated("migrate to original method", ReplaceWith("left contains operand"))
fun <T : Any, S : CharSequence> FilterScopeAll.contains(
    left: ColumnExpression<T, S>,
    operand: CharSequence?,
) {
    left contains operand
}

@Deprecated("migrate to original method", ReplaceWith("left notContains operand"))
fun <T : Any, S : CharSequence> FilterScopeAll.notContains(
    left: ColumnExpression<T, S>,
    operand: CharSequence?,
) {
    left notContains operand
}

@Deprecated("migrate to original method", ReplaceWith("left endsWith operand"))
fun <T : Any, S : CharSequence> FilterScopeAll.endsWith(
    left: ColumnExpression<T, S>,
    operand: CharSequence?,
) {
    left endsWith operand
}

@Deprecated("migrate to original method", ReplaceWith("left notEndsWith operand"))
fun <T : Any, S : CharSequence> FilterScopeAll.notEndsWith(
    left: ColumnExpression<T, S>,
    operand: CharSequence?,
) {
    left notEndsWith operand
}

@Deprecated("migrate to original method", ReplaceWith("left between range"))
fun <T : Comparable<T>, S : Any> FilterScopeAll.between(
    left: ColumnExpression<T, S>,
    range: ClosedRange<T>,
) {
    left between range
}

@Deprecated("migrate to original method", ReplaceWith("left between range"))
fun <T : Comparable<T>, S : Any> FilterScopeAll.between(
    left: ColumnExpression<T, S>,
    range: Pair<ColumnExpression<T, S>, ColumnExpression<T, S>>,
) {
    left between range
}

@Deprecated("migrate to original method", ReplaceWith("left notBetween range"))
fun <T : Comparable<T>, S : Any> FilterScopeAll.notBetween(
    left: ColumnExpression<T, S>,
    range: ClosedRange<T>,
) {
    left notBetween range
}

@Deprecated("migrate to original method", ReplaceWith("left notBetween range"))
fun <T : Comparable<T>, S : Any> FilterScopeAll.notBetween(
    left: ColumnExpression<T, S>,
    range: Pair<ColumnExpression<T, S>, ColumnExpression<T, S>>,
) {
    left notBetween range
}

@Deprecated("migrate to original method", ReplaceWith("left inList values"))
fun <T : Any, S : Any> FilterScopeAll.`in`(
    left: ColumnExpression<T, S>,
    values: List<T?>,
) {
    left inList values
}

@Deprecated("migrate to original method", ReplaceWith("left inList subquery"))
fun <T : Any, S : Any> FilterScopeAll.`in`(
    left: ColumnExpression<T, S>,
    subquery: SubqueryExpression<T?>,
) {
    left inList subquery
}

@Deprecated("migrate to original method", ReplaceWith("left inList block"))
fun <T : Any, S : Any> FilterScopeAll.`in`(
    left: ColumnExpression<T, S>,
    block: () -> SubqueryExpression<T?>,
) {
    left inList block()
}

@Deprecated("migrate to original method", ReplaceWith("left notInList values"))
fun <T : Any, S : Any> FilterScopeAll.notIn(
    left: ColumnExpression<T, S>,
    values: List<T?>,
) {
    left notInList values
}

@Deprecated("migrate to original method", ReplaceWith("left notInList subquery"))
fun <T : Any, S : Any> FilterScopeAll.notIn(
    left: ColumnExpression<T, S>,
    subquery: SubqueryExpression<T?>,
) {
    left notInList subquery
}

@Deprecated("migrate to original method", ReplaceWith("left notInList block"))
fun <T : Any, S : Any> FilterScopeAll.notIn(
    left: ColumnExpression<T, S>,
    block: () -> SubqueryExpression<T?>,
) {
    left notInList block()
}

@Deprecated("migrate to original method", ReplaceWith("left inList2 block"))
fun <A : Any, B : Any> FilterScopeAll.`in2`(
    left: Pair<ColumnExpression<A, *>, ColumnExpression<B, *>>,
    values: List<Pair<A?, B?>>,
) {
    left inList2 values
}

@Deprecated("migrate to original method", ReplaceWith("left inList2 subquery"))
fun <A : Any, B : Any> FilterScopeAll.`in2`(
    left: Pair<ColumnExpression<A, *>, ColumnExpression<B, *>>,
    subquery: SubqueryExpression<Pair<A?, B?>>,
) {
    left inList2 subquery
}

@Deprecated("migrate to original method", ReplaceWith("left inList2 block"))
fun <A : Any, B : Any> FilterScopeAll.`in2`(
    left: Pair<ColumnExpression<A, *>, ColumnExpression<B, *>>,
    block: () -> SubqueryExpression<Pair<A?, B?>>,
) {
    left inList2 block()
}

@Deprecated("migrate to original method", ReplaceWith("left notInList2 values"))
fun <A : Any, B : Any> FilterScopeAll.notIn2(
    left: Pair<ColumnExpression<A, *>, ColumnExpression<B, *>>,
    values: List<Pair<A?, B?>>,
) {
    left notInList2 values
}

@Deprecated("migrate to original method", ReplaceWith("left notInList2 subquery"))
fun <A : Any, B : Any> FilterScopeAll.notIn2(
    left: Pair<ColumnExpression<A, *>, ColumnExpression<B, *>>,
    subquery: SubqueryExpression<Pair<A?, B?>>,
) {
    left notInList2 subquery
}

@Deprecated("migrate to original method", ReplaceWith("left notInList2 block"))
fun <A : Any, B : Any> FilterScopeAll.notIn2(
    left: Pair<ColumnExpression<A, *>, ColumnExpression<B, *>>,
    block: () -> SubqueryExpression<Pair<A?, B?>>,
) {
    left notInList2 block()
}

