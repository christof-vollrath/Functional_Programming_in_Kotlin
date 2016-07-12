// Pure Booleans

abstract class PureBoolean {
    abstract fun <T> ifThenElse(thn: T, els: T): T
    fun and(p: PureBoolean): PureBoolean = ifThenElse(p, pureFalse)
    fun or(p: PureBoolean): PureBoolean = ifThenElse(pureTrue, p)
    fun not(): PureBoolean = ifThenElse(pureFalse, pureTrue)
    fun equals(p: PureBoolean): PureBoolean = ifThenElse(p, p.not())
    fun unequals(p: PureBoolean): PureBoolean = ifThenElse(p.not(), p)
    fun less(p: PureBoolean): PureBoolean = ifThenElse(pureFalse, p)
}

object pureFalse: PureBoolean() {
    override fun<T> ifThenElse(thn: T, els: T) = els
}

object pureTrue: PureBoolean() {
    override fun<T> ifThenElse(thn: T, els: T) = thn
}

// Nat (simple variant of pure integers)

abstract class Nat {
    abstract fun isZero(): Boolean
    abstract fun predecessor(): Nat
    fun successor(): Nat = Succ(this)
    abstract operator fun plus(x: Nat): Nat
    abstract operator fun minus(x: Nat): Nat
}

object zero: Nat() {
    override fun isZero() = true
    override fun predecessor(): Nat = throw IllegalArgumentException()
    override operator fun plus(x: Nat) = x
    override operator fun minus(x: Nat) =
        if (x.isZero()) zero
        else throw IllegalArgumentException()
}

class Succ(val n: Nat): Nat() {
    override fun isZero() = false
    override fun predecessor(): Nat = n
    override operator fun plus(x: Nat) = Succ(predecessor() + x)
    override operator fun minus(x: Nat) =
        if (x.isZero()) this
        else n - x.predecessor()
}

fun Nat.toInt(): Int =
    if (isZero()) 0
    else 1 + predecessor().toInt()

fun Int.toNat(): Nat =
    if(this == 0) zero
    else Succ((this-1).toNat())