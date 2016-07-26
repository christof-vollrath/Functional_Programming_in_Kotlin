import io.kotlintest.specs.FunSpec
import java.util.*
import java.lang.Math.*

class Lecture_Collections_Test : FunSpec() { init {

    // Arrays used like lists
    test("Filter positive element in Array") {
        arrayOf(1,-2,3).filter { it > 0 } shouldBe listOf(1,3)
    }

    // String used like lists
    test("Chars positive element in String") {
        "abcd".filter { it > 'b' } shouldBe "cd"
    }

    // Ranges
    test("Filter positive element in Range") {
        (-3..3).filter { it > 0 } shouldBe listOf(1,2,3)
    }

    test("Filter positive element in Range with steps") {
        (-3..3 step 2).filter { it > 0 } shouldBe listOf(1,3)
    }

    test("Filter positive element in Down-Range with steps") {
        (3 downTo -3 step 2).filter { it > 0 } shouldBe listOf(3,1   )
    }

    // More collection functions

    test("Check if an element exists fullfilling a condition") {
        (1..20).any({it == 10}) shouldBe true
    }

    test("Check if for all element the condition is fullfilled") {
        (0..20 step 2).all({it % 2 == 0}) shouldBe true
    }

    test("Zip and unzip") {
        listOf(1,2,3).zip(listOf(4,5,6)).unzip() shouldBe Pair(listOf(1,2,3), listOf(4,5,6))
    }

    test("Zip and unzip of mixed types") {
        (1..16).zip("Alles in Butter".asIterable()).unzip() shouldBe Pair((1..15).toList(), "Alles in Butter".toList())
    }
    test("flatmap") {
        "Alles in Butter".flatMap {listOf('.', it)}.joinToString("") shouldBe(".A.l.l.e.s. .i.n. .B.u.t.t.e.r")
    }

    // Examples

    // Combination of two numbers from ranges
    fun combination(range1: IntRange, range2: IntRange): List<Pair<Int, Int>> =
            range1.flatMap { i1 -> range2.map { i2 -> Pair(i1, i2)}}
    test("Combinations of [1, 3] and [2,4]") {
        combination(1..3, 2..4) shouldBe listOf(Pair(1,2),Pair(1,3),Pair(1,4),Pair(2,2),Pair(2,3),Pair(2,4),Pair(3,2),Pair(3,3),Pair(3,4))
    }

    // Scalar product of vector
    fun scalarProduct(v1: Collection<Double>, v2: Collection<Double>): Double =
            v1.zip(v2).map { val (x, y) = it; x * y }.sum()
    test("Scalar product of [1,2,3] and [2,2,2] should be 12") {
        scalarProduct(listOf(1.0,2.0,3.0), listOf(2.0,2.0,2.0)) shouldBe 12.0
    }

    // High-level test for prime
    fun isPrime(i: Int): Boolean = ! (2..(i/2)).any { i % it == 0 }
    test("Prime") {
        isPrime(2) shouldBe true
        isPrime(3) shouldBe true
        isPrime(4) shouldBe false
        isPrime(29) shouldBe true
        isPrime(101) shouldBe true
        isPrime(105) shouldBe false
        isPrime(841) shouldBe false
        isPrime(10201) shouldBe false
    }
} }

