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

    test("flatten") {
        listOf(listOf(1,2,3), listOf(4,5,6), listOf(7,8,9)).flatten() shouldBe listOf(1,2,3,4,5,6,7,8,9)
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
        scalarProduct(listOf(1.0,2.0,3.0), listOf(2.0,2.0,2.0)) shouldBe (12.0 plusOrMinus 0.1)
    }

    // High-level test for prime
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

    // Example Lecture 6.2
    test("pairsSumPrime according to example for n = 7") {
        pairsSumPrime(7) shouldBe listOf(Pair(2,1),Pair(3,2),Pair(4,1),Pair(4,3),Pair(5,2),Pair(6,1),Pair(6,5))
    }
    test("pairsSumPrime2 according to example for n = 7") {
        pairsSumPrime2(7) shouldBe listOf(Pair(2,1),Pair(3,2),Pair(4,1),Pair(4,3),Pair(5,2),Pair(6,1),Pair(6,5))
    }
    test("pairsSumPrime3 according to example for n = 7") {
        pairsSumPrime3(7) shouldBe listOf(Pair(2,1),Pair(3,2),Pair(4,1),Pair(4,3),Pair(5,2),Pair(6,1),Pair(6,5))
    }
    test("pairsSumPrime4 according to example for n = 7") {
        pairsSumPrime4(7) shouldBe listOf(Pair(2,1),Pair(3,2),Pair(4,1),Pair(4,3),Pair(5,2),Pair(6,1),Pair(6,5))
    }

    // Sets
    test("simple set") {
        setOf("apple", "banana", "pear").contains("pear") shouldBe true
    }
    test("Set from range") {
        (1..3).toSet().contains(2) shouldBe true
    }
    test("Sets should not contain duplicates") {
        setOf(1,2,3,1).size shouldBe 3 // Duplicate 1 ignored
    }
    test("Map on sets") {
        setOf(1,2,3).map{it+1}.toSet() shouldBe setOf(2,3,4)
    }

    // Queens
    test("positionsWithRow should add column") {
        positionsWithRow(listOf(0,3,1)) shouldBe listOf(Pair(2,0), Pair(1,3), Pair(0,1))
    }
    test("isSafe should return false if queens are in same column") {
        isSafe(0, listOf(0)) shouldBe false
        isSafe(2, listOf(0,1,2)) shouldBe false
    }
    test("isSafe should return false if queens are in same row") {
        isSafe(1, listOf(0)) shouldBe false
    }
    test("isSafe should return true if queens are not in same row") {
        isSafe(0, listOf(1,3)) shouldBe true
    }
    test("extendSolution should return the single position for n = 1") {
        extendSolution(1, emptyList<Int>()) shouldBe setOf(listOf(0))
    }
    test("extendSolution should return two positions for n = 2") {
        extendSolution(1, listOf(0)) shouldBe setOf(listOf(0),listOf(1))
    }
    test("queens for n = 2") {
        queens(2) shouldBe setOf(listOf(0,2))
    }
} }

