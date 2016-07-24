import io.kotlintest.specs.FunSpec
import java.util.*

class Lecture_Lists_Test : FunSpec() { init {

    // More Functions on Lists

    test("Length of list") {
        listOf(1,2,3).size shouldBe 3
        listOf<Any>().size shouldBe 0
    }
    test("Last element of list") {
        listOf(1,2,3).last() shouldBe 3
    }
    test("Last element of empty list should through exception") {
        shouldThrow<NoSuchElementException> {
            listOf<Any>().last()
        }
    }
    test("Get returns element from list") {
        listOf("One","Two","Three").get(1) shouldBe "Two"
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
} }

