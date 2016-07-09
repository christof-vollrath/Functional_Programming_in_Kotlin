import java.util.*

// 3.1 Class Hierarchies

// IntSet

abstract class IntSet {
    abstract fun incl(x: Int): IntSet
    abstract fun contains(x: Int): Boolean
    abstract fun union(set: IntSet): IntSet
}

object Empty: IntSet() {
    override fun incl(x: Int): IntSet = NonEmpty(x, Empty, Empty)
    override fun contains(x: Int): Boolean = false
    override fun toString() = "."
    override fun union(set: IntSet): IntSet = set
}

class NonEmpty(val elem: Int, val left: IntSet, val right: IntSet) : IntSet() {
    override fun incl(x: Int): IntSet =
        if (x < elem) NonEmpty(elem, left.incl(x), right)
        else if (x > elem) NonEmpty(elem, left, right.incl(x))
        else this
    override fun contains(x: Int): Boolean =
        if (x < elem) left.contains(x)
        else if (x > elem) right.contains(x)
        else true
    override fun toString() = "{$left$elem$right}"
    override fun union(set: IntSet): IntSet =
        set.union(right).union(left).incl(elem)

}


// 3.3 Parameterized Types

// ConsSet

interface ConsList<T> {
    val empty: Boolean
    val head: T
    val tail: ConsList<T>
}

class Nil<T>: ConsList<T> {
    override val empty = true
    override val head: Nothing get() { throw NoSuchElementException() }
    override val tail: Nothing get() { throw NoSuchElementException() }
}

class Cons<T>(override val head: T, override val tail: ConsList<T>): ConsList<T> {
    override val empty = false
}

fun <T> singleton(elem: T) = Cons<T>(elem, Nil<T>())

fun <T> nth(n: Int, list: ConsList<T>): T =
    if (list.empty) throw IndexOutOfBoundsException()
    else if (n == 0) list.head
    else nth(n-1, list.tail)

// Type erasure

// While Java and Scala have type erasure,
// in Kotlin the combination of inline and reified allows access to runtime types

inline fun <reified T> typeOfParam(p: T): String = T::class.qualifiedName ?: "Nothing"
