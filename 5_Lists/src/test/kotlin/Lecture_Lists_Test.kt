import io.kotlintest.specs.FunSpec
import java.util.*
import java.lang.Math.*

class Lecture_Lists_Test : FunSpec() { init {

    // More Functions on Lists

    test("Length of list") {
        listOf(1,2,3).size shouldBe 3
        emptyList<Nothing>().size shouldBe 0
    }
    test("Last element of list") {
        listOf(1,2,3).last() shouldBe 3
    }
    test("Last element of empty list should through exception") {
        shouldThrow<NoSuchElementException> {
            emptyList<Nothing>().last()
        }
    }
    test("Get returns element from list") {
        listOf("One","Two","Three")[1] shouldBe "Two"
    }
    test("Drop returns sublist from list") {
        listOf("One","Two","Three").drop(1) shouldBe listOf("Two","Three")
    }
    test("Sublist works similar to drop, but does not create a new list") {
        listOf("One","Two","Three").subList(1, 3) shouldBe listOf("Two","Three")
    }
    test("List concat") {
        listOf(1,2,3) + listOf(4,5) shouldBe listOf(1,2,3,4,5)
    }
    test("Reverse list") {
        listOf(1,2,3).reversed() shouldBe listOf(3,2,1)
    }
    test("Find element in list") {
        listOf(1,2,3).find { it == 2 } shouldBe 2
    }
    test("Index of element in list") {
        listOf("One","Two","Three").indexOf("Two") shouldBe 1
    }
    test("Check if list contains an element") {
        listOf(1,2,3).contains(2) shouldBe true
    }

    // Pairs but no Tuples in Kotlin

    test("Accessing pair elements") {
        val p  = Pair("one", 1)
        p.first shouldBe "one"
        p.second shouldBe 1
    }
    test("Assigning pair to variables") {
        val p  = Pair("one", 1)
        val (p1, p2) = p
        p1 shouldBe "one"
        p2 shouldBe 1
    }

    test("Assigning list to variable pair") {
        val (e1, e2) = listOf(1,2)
        e1 shouldBe 1
        e2 shouldBe 2
    }
    test("Assigning list to variable tuple") {
        val (e1, e2, e3) = listOf(1,2,3)
        e1 shouldBe 1
        e2 shouldBe 2
        e3 shouldBe 3
    }
    test("Assigning list to variable tuple missing one element should throw exception") {
        shouldThrow<ArrayIndexOutOfBoundsException> {
            val (e1, e2, e3) = listOf(1,2)
            e1
        }
    }

    // Higher-Order List Functions

    fun scaleList(list: List<Double>, factor: Double) = list.map {it * factor}

    test("Multiply every element") {
        scaleList(listOf(1.0,2.0,3.0), 5.0) shouldBe listOf(5.0,10.0,15.0)
    }

    test("Filter positive element") {
        listOf(1,-2,3).filter { it > 0 } shouldBe listOf(1,3)
    }

    test("Keeps dropping as long as condition is fullfilled") {
        listOf(1,-2,3,4,-5).dropWhile { it < 3 }  shouldBe listOf(3, 4, -5)
    }

    test("Dropping as long as condition is fullfilled starting from the end") {
        listOf(1,-2,3,4,-5).dropLastWhile { it < 3 }  shouldBe listOf(1,-2,3,4)
    }

    test("Keep as long as condition is fullfilled") {
        listOf(1,-2,3,4,-5).takeWhile { it < 3 }  shouldBe listOf(1,-2)
    }
    test("Keep as long as condition is fullfilled from the end") {
        listOf(1,-2,3,4,-5).takeLastWhile { it < 3 }  shouldBe listOf(-5)
    }

    test("Splits list") {
        val (a, b) = listOf(1,-2,3,4,-5).partition { abs(it) <= 3 }
        a shouldBe listOf(1,-2,3)
        b shouldBe listOf(4, -5)
    }

    test("zip lists") {
        listOf(1,2,3).zip(listOf(4,5,6)) shouldBe listOf(Pair(1,4),Pair(2,5),Pair(3,6))
    }

    test("zip lists with different length") {
        listOf(1,2,3).zip(listOf(4,5,6,7)) shouldBe listOf(Pair(1,4),Pair(2,5),Pair(3,6))
    }

    test("pack exercise") {
        pack(listOf<Int>()) shouldBe listOf<List<Int>>()
        pack(listOf('a')) shouldBe listOf(listOf('a'))
        pack(listOf("a", "a", "a", "b", "c", "c", "a")) shouldBe listOf(listOf("a", "a", "a"), listOf("b"), listOf("c", "c"), listOf("a"))
    }
    test("encode exercise") {
        encode(listOf<Char>()) shouldBe listOf<Pair<Char, Int>>()
        encode(listOf('a')) shouldBe listOf(Pair('a', 1))
        encode(listOf("a", "a", "a", "b", "c", "c", "a")) shouldBe listOf(Pair("a", 3), Pair("b", 1), Pair("c", 2), Pair("a",1))
    }

    // Reduction of lists
    test("sum over integer list") {
        // using reduce
        listOf(1,2,3).reduce { r, t -> r + t } shouldBe 6

        // using fold
        listOf<Int>().fold(0) { r, t -> r + t } shouldBe 0
        listOf(1,2,3).fold(0) { r, t -> r + t } shouldBe 6
        listOf(1,2,3).foldRight(0) { r, t -> r + t } shouldBe 6 // Addition is associative
    }
    test("Empty list can not be reduced") {
        shouldThrow<UnsupportedOperationException> {
            listOf<Int>().reduce { r, t -> r + t }
        }
    }
    test("Reverse using foldRight") {
        listOf(1,2,3).foldRight(listOf<Int>()) {t, r -> r + t} shouldBe listOf(3,2,1)
    }
    test("Concat through foldRight") {
       listOf(1,2,3).foldRight(listOf(4,5,6)) {t, r -> listOf(t) + r} shouldBe listOf(1,2,3,4,5,6)
    }

    fun <T,U> mapThroughFold(list: List<T>, mapFunct: (T) -> U): List<U> =
        list.fold(listOf<U>()) {r, t -> r + mapFunct(t)}
    test("mapThroughFold should apply to every element") {
        mapThroughFold(listOf(1.0,2.0,3.0), {it * 5.0}) shouldBe listOf(5.0,10.0,15.0)
    }
    fun <T,U> mapThroughFoldRight(list: List<T>, mapFunct: (T) -> U): List<U> =
            list.foldRight(listOf<U>()) {t, r -> listOf(mapFunct(t)) + r}
    test("mapThroughFoldRight should apply to every element") {
        mapThroughFoldRight(listOf(1.0,2.0,3.0), {it * 5.0}) shouldBe listOf(5.0,10.0,15.0)
    }

    fun <T> lengthThroughFold(list: List<T>): Int =
            list.fold(0) {r, t -> r + 1}
    test("lengthThroughFold should return size") {
        lengthThroughFold(listOf(1,2,3)) shouldBe 3
    }
} }

