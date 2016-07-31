import io.kotlintest.specs.FunSpec

class AnagramsSuite: FunSpec() { init {

    test("wordOccurrences: abcd") {
        wordOccurrences("abcd") shouldBe listOf(Pair('a', 1), Pair('b', 1), Pair('c', 1), Pair('d', 1))
    }

    test("wordOccurrences: Robert") {
        wordOccurrences("Robert") shouldBe listOf(Pair('b', 1), Pair('e', 1), Pair('o', 1), Pair('r', 2), Pair('t', 1))
    }


    test("sentenceOccurrences: e dabc") {
        sentenceOccurrences(listOf("e", "dabc")) shouldBe listOf(Pair('a', 1), Pair('b', 1), Pair('c', 1), Pair('d', 1), Pair('e', 1))
    }

    test("create dictionaryByOccurrences") {
        val map = createDictionaryByOccurences(listOf("ate", "eat", "tea"))
        map[listOf(Pair('a', 1), Pair('e', 1), Pair('t', 1))] shouldBe listOf("ate", "eat", "tea")
    }

    test("dictionaryByOccurrences.get: eat") {
        dictionaryByOccurrences[listOf(Pair('a', 1), Pair('e', 1), Pair('t', 1))]?.toSet() shouldBe setOf("ate", "eat", "tea")
    }

    test("dictionaryByOccurrences.get: rulez") {
        dictionaryByOccurrences[listOf(Pair('e', 1), Pair('l', 1), Pair('r', 1), Pair('u', 1), Pair('z', 1))]?.toSet() shouldBe setOf("rulez")
    }

    test("word anagrams: married") {
        wordAnagrams("married").toSet() shouldBe setOf("married", "admirer")
    }

    test("word anagrams: player") {
        wordAnagrams("player").toSet() shouldBe setOf("parley", "pearly", "player", "replay")
    }


    test("subtract: empty - empty") {
        subtract(listOf(), listOf()) shouldBe listOf<Pair<Char, Int>>()
    }


    test("subtract: lard - r") {
        val lard = listOf(Pair('a', 1), Pair('d', 1), Pair('l', 1), Pair('r', 1))
        val r = listOf(Pair('r', 1))
        val lad = listOf(Pair('a', 1), Pair('d', 1), Pair('l', 1))
        subtract(lard, r) shouldBe lad
    }

    test("simpleCombinations: emptyList") {
        simpleCombinations(emptyList<Int>()) shouldBe listOf(emptyList<Int>())
    }

    test("simpleCombinations: (1)") {
        simpleCombinations(listOf(1)) shouldBe listOf(listOf(), listOf(1))
    }

    test("simpleCombinations: (1,2)") {
        simpleCombinations(listOf(1,2)) shouldBe listOf(listOf(), listOf(2), listOf(1), listOf(1,2))
    }

    test("simpleCombinations: (1,2,3)") {
        simpleCombinations(listOf(1,2,3)).toSet() shouldBe setOf(listOf(), listOf(2), listOf(1), listOf(1,2), listOf(3), listOf(2,3), listOf(1,3), listOf(1,2,3))
    }

    test("combinations: []") {
        combinations(listOf<Pair<Char, Int>>()) shouldBe listOf(listOf<Pair<Char, Int>>())
    }

    test("combinations: aa") {
        combinations(listOf(Pair('a', 2))).toSet() shouldBe listOf(listOf(), listOf(Pair('a', 1)), listOf(Pair('a', 2))).toSet()
    }

    test("combinations: aaa") {
        combinations(listOf(Pair('a', 3))).toSet() shouldBe listOf(listOf(), listOf(Pair('a', 1)), listOf(Pair('a', 2)), listOf(Pair('a', 3))).toSet()
    }

    test("combinations: baa") {
        combinations(listOf(Pair('a', 2), Pair('b', 1))).toSet() shouldBe listOf(listOf(), listOf(Pair('a', 1)), listOf(Pair('a', 2)), listOf(Pair('b', 1)), listOf(Pair('a', 1), Pair('b', 1)), listOf(Pair('a', 2), Pair('b', 1))).toSet()
    }
    test("combinations: abba") {
        val abba = listOf(Pair('a', 2), Pair('b', 2))
        val abbacomb = listOf(
                listOf(),
                listOf(Pair('a', 1)),
        listOf(Pair('a', 2)),
        listOf(Pair('b', 1)),
        listOf(Pair('a', 1), Pair('b', 1)),
        listOf(Pair('a', 2), Pair('b', 1)),
        listOf(Pair('b', 2)),
        listOf(Pair('a', 1), Pair('b', 2)),
        listOf(Pair('a', 2), Pair('b', 2))
        )
        combinations(abba).toSet() shouldBe abbacomb.toSet()
    }


    test("sentence anagrams: []") {
        val sentence = listOf<String>()
        sentenceAnagrams(sentence) shouldBe listOf(listOf<String>())
    }

    test("sentence anagrams: tea") {
        val sentence = listOf("tea")
        sentenceAnagrams(sentence) shouldBe listOf(listOf("ate"), listOf("eat"), listOf("tea"))
    }
    test("sentence anagrams: tea tea") {
        val sentence = listOf("teatea")
        sentenceAnagrams(sentence).size shouldBe 9 // all combinations of tea anagrams
    }

    test("sentence anagrams: Linux rulez") {
        val sentence = listOf("Linux", "rulez")
        val anas = listOf(
                listOf("Rex", "Lin", "Zulu"),
                listOf("nil", "Zulu", "Rex"),
                listOf("Rex", "nil", "Zulu"),
                listOf("Zulu", "Rex", "Lin"),
                listOf("null", "Uzi", "Rex"),
                listOf("Rex", "Zulu", "Lin"),
                listOf("Uzi", "null", "Rex"),
                listOf("Rex", "null", "Uzi"),
                listOf("null", "Rex", "Uzi"),
                listOf("Lin", "Rex", "Zulu"),
                listOf("nil", "Rex", "Zulu"),
                listOf("Rex", "Uzi", "null"),
                listOf("Rex", "Zulu", "nil"),
                listOf("Zulu", "Rex", "nil"),
                listOf("Zulu", "Lin", "Rex"),
                listOf("Lin", "Zulu", "Rex"),
                listOf("Uzi", "Rex", "null"),
                listOf("Zulu", "nil", "Rex"),
                listOf("rulez", "Linux"),
                listOf("Linux", "rulez")
        )
        sentenceAnagrams(sentence).toSet() shouldBe anas.toSet()
    }

}}
