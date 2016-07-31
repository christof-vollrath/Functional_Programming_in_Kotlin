import io.kotlintest.specs.FunSpec

class Lecture_Collections_Test: FunSpec() { init {

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
        setOf("apple", "banana", "pear") should contain("pear")
    }
    test("Set from range") {
        (1..3).toSet() should contain(2)
    }
    test("Sets should not contain duplicates") {
        setOf(1,2,3,1).size shouldBe 3 // Duplicate 1 ignored
    }
    test("Map on sets") {
        setOf(1,2,3).map{it+1}.toSet() shouldBe setOf(2,3,4)
    }

    // Queens
    test("positionsWithRow should add column") {
        positionsWithRow(listOf(0,3,1)) shouldBe setOf(Pair(2,0), Pair(1,3), Pair(0,1))
    }
    test("isSafe") {
        val textTab = table(
                headers("pos",   "queens",                             "expected"),
                row(Pair(1,0), setOf(Pair(0,0)),                       false), // same col
                row(Pair(3,1), setOf(Pair(2,0),Pair(1,1),Pair(0,2)),   false),
                row(Pair(1,1), setOf(Pair(0,0)),                       false), // diagonal
                row(Pair(0,0), setOf(Pair(1,1)),                       false),
                row(Pair(3,3), setOf(Pair(0,0),Pair(1,0),Pair(2,0)),   false),
                row(Pair(0,1), setOf(Pair(0,0)),                       false), // same row
                row(Pair(3,1), setOf(Pair(0,0)),                       true),
                row(Pair(3,2), setOf(Pair(0,1),Pair(1,3),Pair(2,0)),   true)
        )
        forAll(textTab) { pos, queens, expected ->
            isSafe(pos, queens) shouldBe expected
        }

    }
    test("queens for n = 1") {
        queens(1) shouldBe setOf(listOf(0))
    }
    test("queens for n = 4") {
        queens(4) shouldBe setOf(listOf(1,3,0,2), listOf(2,0,3,1))
    }
    test("queens for n = 8") {
        val queensSet = queens(8)
        for (queens in queensSet)
            println(showQueens(queens))
        queensSet.size shouldBe 92 // See https://en.wikipedia.org/wiki/Eight_queens_puzzle
    }

    // Maps
    test("Creating map and accessing") {
        val map = mapOf(1 to "one", 2 to "two", 3 to "three")
        map.size shouldBe 3
        map[1] shouldBe "one"
        map[2] shouldBe "two"
        map[3] shouldBe "three"
        map[4] shouldBe null
    }

    test("Adding to existing map and changing") {
        val map = mapOf(1 to "one", 2 to "two", 3 to "three")
        val map2 = map + (4 to "four")
        map2.size shouldBe 4
        map2[4] shouldBe "four"

        val map3 = mutableMapOf(1 to "one", 2 to "two", 3 to "three")
        map3 += (2 to "deux")
        map3[2] shouldBe "deux"
    }

    test("to in maps idiom is like Pair") {
        Pair(1,2) shouldBe (1 to 2)
    }

    test("Iterating over maps") {
        val map =  mapOf(1 to "one", 2 to "two", 3 to "three")
        val sb = StringBuffer()
        for ((k,v) in map) {
            sb.append(v + " ")
        }
        sb.toString() shouldBe "one two three "
    }

    // Sorting and Grouping

    test("Sort list") {
        val fruits = listOf("apple", "pear", "orange", "pineapple")
        fruits.groupBy { it[0] } shouldBe mapOf('a' to listOf("apple"), 'p' to listOf("pear", "pineapple"), 'o' to listOf("orange"))
    }

    test("Group list") {
        val fruits = listOf("apple", "pear", "orange", "pineapple")
        fruits.groupBy { it[0] } shouldBe mapOf('a' to listOf("apple"), 'p' to listOf("pear", "pineapple"), 'o' to listOf("orange"))
    }

    // Variable arguments

    test("Function with varargs") {
        fun parametersToList(vararg p: Int) =
                p.toList()

        parametersToList(1,2,3) shouldBe listOf(1,2,3)
    }

    class WithVarargs(vararg p: Int) {
        val ints: List<Int>
        init {
            ints = p.toList()
        }
    }

    test("Class with varargs constructor") {
        WithVarargs(1,2,3).ints shouldBe listOf(1,2,3)
    }

    class WithVarargsInSecondaryConstructor(val ints: List<Int>) {
        constructor(vararg p: Int): this(p.toList())
    }

    test("Class with varargs constructor in secondary constructor") {
        WithVarargsInSecondaryConstructor(1,2,3).ints shouldBe listOf(1,2,3)
    }

    // Polynomials

    test("Polynomial toString") {
        Polynomial(mapOf()).toString() shouldBe ""
        Polynomial(mapOf(1 to -2.0, 3 to 1.0, 0 to 5.0)).toString() shouldBe "x^3-2.0x+5.0"
    }

    test("Polynomial +") {
        val p1 = Polynomial(1 to 2.0, 3 to 4.0, 5 to 6.2)
        val p2 = Polynomial(0 to 3.0, 3 to 7.0)
        p1 + p2 shouldBe Polynomial(5 to 6.2, 3 to 11.0, 1 to 2.0, 0 to 3.0)
    }

    // Phone numbers to words

    test("toMnemonics") {
        toMnemonics("7225247386") should contain(listOf("Scala","is","fun"))
    }

    test("translate") {
        translate("7225247386") should contain("Scala is fun")
    }

    test("loadDictionary from file") {
        words.size shouldBe 45374-20 // 20 words not contain only letters
    }

    test("charCode") {
        charCode['A'] shouldBe '2'
        charCode['C'] shouldBe '2'
        charCode['Q'] shouldBe '7'
        charCode['Z'] shouldBe '9'
    }

    test("wordCode") {
        wordCode("Scalaisfun") shouldBe "7225247386"
    }

    test("wordsForNum") {
        println(wordsForNum["72252"])
        wordsForNum["72252"]?.contains("Scala") shouldBe true
    }
}}

