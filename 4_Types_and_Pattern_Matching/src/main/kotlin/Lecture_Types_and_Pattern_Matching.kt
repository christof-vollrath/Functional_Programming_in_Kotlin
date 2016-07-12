// Pure Booleans

abstract class PureBoolean {
    abstract fun <T> ifThenElse(thn: T, els: T): T
    fun and(p: PureBoolean): PureBoolean = ifThenElse(p, pureFalse)
    fun or(p: PureBoolean): PureBoolean = ifThenElse(pureTrue, p)
    fun not(): PureBoolean = ifThenElse(pureFalse, pureTrue)
    fun equals(p: PureBoolean): PureBoolean = ifThenElse(p, p.not())
    fun unequals(p: PureBoolean): PureBoolean = ifThenElse(p.not(), p)
}

object pureFalse: PureBoolean() {
    override fun<T> ifThenElse(thn: T, els: T) = els
}

object pureTrue: PureBoolean() {
    override fun<T> ifThenElse(thn: T, els: T) = thn
}

