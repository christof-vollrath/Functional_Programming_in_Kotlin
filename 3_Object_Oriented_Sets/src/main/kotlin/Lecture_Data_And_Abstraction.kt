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
