import java.util.*

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
// Peano numbers

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

// Expressions of Number and Sum

interface Expr {
    fun eval(): Int
}

class Num(val numValue: Int): Expr {
    override fun eval() = numValue
}

class Sum(val e1: Expr, val e2: Expr): Expr {
    override fun eval() = e1.eval() + e2.eval()
}

class Prod(val e1: Expr, val e2: Expr): Expr {
    override fun eval() = e1.eval() * e2.eval()
}

fun show(expr: Expr): String =
    when (expr) {
        is Num -> expr.numValue.toString()
        is Sum -> {
            show(expr.e1) + " + " + show(expr.e2)
        }
        is Prod -> {
            val parentheses1 = parenthesesNeeded(expr.e1, expr)
            val parentheses2 = parenthesesNeeded(expr.e2, expr)
            (if (parentheses1) "(" else "") +
            show(expr.e1) +
            (if (parentheses1) ")" else "") +
            " * " +
            (if (parentheses2) "(" else "") +
            show(expr.e2) +
            (if (parentheses2) ")" else "")
        }
        else -> throw IllegalArgumentException("Unkown Expr subtype: ${expr.javaClass}")
    }

internal fun parenthesesNeeded(expr: Expr, parentExpr: Expr): Boolean =
    when (parentExpr) {
        is Prod ->
            when (expr) {
                is Sum -> true
                else -> false
            }
        else -> false
    }

// Variance

interface Function1<in T, out U> {
    fun apply(x: T): U
}

interface Function1a<in T, out U> {
//    fun apply(x: U): T  // Compiler checks if in, out are used correctly
}

abstract class IntList {}
class NonEmptyList: IntList() {}
class EmptyList: IntList() {}

class A: Function1<IntList, NonEmptyList> {
    override fun apply(x: IntList): NonEmptyList = NonEmptyList()
}

class B: Function1<NonEmptyList, IntList> {
    override fun apply(x: NonEmptyList): IntList = NonEmptyList()
}

val a1: Function1<IntList, NonEmptyList> = A()
val b1: Function1<NonEmptyList, IntList> = a1
val b2: Function1<NonEmptyList, IntList> = B()
//val a2: Function1<IntList, NonEmptyList> = b2 // Type mismatch
// Function subtyping: argument types must be contravariant, result types must be covariant

interface MyList<out T> {
    fun isEmpty(): Boolean
    val head: T
    val tail: MyList<T>
}

class Cons<T>(override val head: T, override val tail: MyList<T>): MyList<T> {
    override fun isEmpty(): Boolean = false
}
object Nil: MyList<Nothing> {
    override val head: Nothing
        get() { throw NoSuchElementException("head of EmptyList") }
    override val tail: Nothing
        get() { throw NoSuchElementException("tail of EmptyList") }
    override fun isEmpty() = true
}

val lNil = Nil
val l1 = Cons("e1", Nil) // If List<out T> would be declared without out, we would have a type error at Nil

fun<U> prepend(elem: U, list: MyList<U>): MyList<U> = Cons(elem, list) // Can not be member function of MyList, since a different type is returned
val intList = Cons(1, Nil)
val numList: MyList<Number> = prepend(2.0, intList)
// val StringList: MyList<String> = prepend("2", intList) // Type error because types are not covariant