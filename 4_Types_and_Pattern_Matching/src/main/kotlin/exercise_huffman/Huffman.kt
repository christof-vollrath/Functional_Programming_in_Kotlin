package exercise_huffman

import head
import tail

/**
 * Assignment 4: Huffman coding
 *
 */
object Huffman {

    /**
     * A huffman code is represented by a binary tree.
     *
     * Every `Leaf` node of the tree represents one character of the alphabet that the tree can encode.
     * The weight of a `Leaf` is the frequency of appearance of the character.
     *
     * The branches of the huffman tree, the `Fork` nodes, represent a set containing all the characters
     * present in the leaves below it. The weight of a `Fork` node is the sum of the weights of these
     * leaves.
     */
    abstract class CodeTree {}

    class Fork(val left: CodeTree, val right: CodeTree, val chars: List<Char>, val weight: Int) : CodeTree() {
        override fun equals(other: Any?): Boolean { // Unfortunately equals must be implemented since data classes have no inheritance in Kotlin 1.0
            if (this === other) return true
            if (other?.javaClass != javaClass) return false

            other as Fork

            if (left != other.left) return false
            if (right != other.right) return false
            if (chars != other.chars) return false
            if (weight != other.weight) return false

            return true
        }

        override fun toString(): String {
            return "Fork(left=$left, right=$right, chars=$chars, weight=$weight)"
        }
    }

    class Leaf(val char: Char, val weight: Int) : CodeTree() { // Unfortunately equals must be implemented too
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other?.javaClass != javaClass) return false

            other as Leaf

            if (char != other.char) return false
            if (weight != other.weight) return false

            return true
        }

        override fun toString(): String {
            return "Leaf(char=$char, weight=$weight)"
        }

    }


    // Part 1: Basics
    fun weight(tree: CodeTree): Int = // tree match ...
            when (tree) {
                is Fork -> tree.weight
                is Leaf -> tree.weight
                else -> throw IllegalArgumentException("Unkown CodeTree subtype: ${tree.javaClass}")
            }


    fun chars(tree: CodeTree): List<Char> = // tree match ...
            when (tree) {
                is Fork -> tree.chars
                is Leaf -> listOf(tree.char)
                else -> throw IllegalArgumentException("Unkown CodeTree subtype: ${tree.javaClass}")
            }

    fun makeCodeTree(left: CodeTree, right: CodeTree) =
            Fork(left, right, chars(left) + chars(right), weight(left) + weight(right))


    // Part 2: Generating Huffman trees

    /**
     * In this assignment, we are working with lists of characters. This function allows
     * you to easily create a character list from a given string.
     */
    fun string2Chars(str: String): List<Char> = str.toList()

    /**
     * This function computes for each unique character in the list `chars` the number of
     * times it occurs. For example, the invocation
     *
     *   times(List('a', 'b', 'a'))
     *
     * should return the following (the order of the resulting list is not important):
     *
     *   List(('a', 2), ('b', 1))
     *
     * The type `List[(Char, Int)]` denotes a list of pairs, where each pair consists of a
     * character and an integer. Pairs can be constructed easily using parentheses:
     *
     *   val pair: (Char, Int) = ('c', 1)
     *
     * In order to access the two elements of a pair, you can use the accessors `_1` and `_2`:
     *
     *   val theChar = pair._1
     *   val theInt  = pair._2
     *
     * Another way to deconstruct a pair is using pattern matching:
     *
     *   pair match {
     *     case (theChar, theInt) =>
     *       println("character is: "+ theChar)
     *       println("integer is  : "+ theInt)
     *   }
     */
    fun times(chars: List<Char>): List<Pair<Char, Int>> =
            if (chars.isEmpty()) listOf()
            else mergeTimes(chars.head(), times(chars.tail()))

    internal fun mergeTimes(c: Char, pairs: List<Pair<Char, Int>>): List<Pair<Char, Int>> =
            if (pairs.isEmpty()) listOf(Pair(c, 1))
            else if (c == pairs.head().first) listOf(Pair(c, pairs.head().second + 1)) + pairs.tail()
            else listOf(pairs.head()) + mergeTimes(c, pairs.tail())

    /**
     * Returns a list of `Leaf` nodes for a given frequency table `freqs`.
     *
     * The returned list should be ordered by ascending weights (i.e. the
     * head of the list should have the smallest weight), where the weight
     * of a leaf is the frequency of the character.
     */
    fun makeOrderedLeafList(freqs: List<Pair<Char, Int>>): List<Leaf> =
            if (freqs.isEmpty()) listOf<Leaf>()
            else {
                val max = highestLeaf(freqs)
                listOf(max) + makeOrderedLeafList(removeTimes(max.char, freqs))
            }

    internal fun highestLeaf(freqs: List<Pair<Char, Int>>): Leaf {
        val head = freqs.head()
        val headLeaf = Leaf(head.first, head.second)
        val tail = freqs.tail()
        if (tail.isEmpty()) return headLeaf
        else {
            val maxTail = highestLeaf(tail)
            if (headLeaf.weight < maxTail.weight) return maxTail
            else return headLeaf
        }
    }

    internal fun removeTimes(c: Char, pairs: List<Pair<Char, Int>>): List<Pair<Char, Int>> =
            if (pairs.isEmpty()) pairs
            else {
                val head = pairs.head()
                if (head.first == c) pairs.tail() // Only once in list
                else listOf(head) + removeTimes(c, pairs.tail())
            }


    /**
     * Checks whether the list `trees` contains only one single code tree.
     */
    fun singleton(trees: List<CodeTree>): Boolean = trees.size <= 1

    /**
     * The parameter `trees` of this function is a list of code trees ordered
     * by ascending weights.
     *
     * This function takes the first two elements of the list `trees` and combines
     * them into a single `Fork` node. This node is then added back into the
     * remaining elements of `trees` at a position such that the ordering by weights
     * is preserved.
     *
     * If `trees` is a list of less than two elements, that list should be returned
     * unchanged.
     */
    fun combine(trees: List<CodeTree>): List<CodeTree> =
            if (trees.isEmpty()) trees
            else {
                val firstTree = trees.head()
                val tailTree = trees.tail()
                if (tailTree.isEmpty()) listOf(firstTree)
                else insertOrdered(combineTwo(firstTree, tailTree.first()), tailTree.tail())
            }

    internal fun combineTwo(first: CodeTree, second: CodeTree): CodeTree {
        val leftIsFirst = weight(first) > weight(second)
        val left = if (leftIsFirst) first else second
        val right = if (!leftIsFirst) first else second
        return Fork(left, right, chars(left) + chars(right), weight(left) + weight(right))
    }

    internal fun insertOrdered(tree: CodeTree, list: List<CodeTree>): List<CodeTree> =
            if (list.isEmpty()) listOf(tree)
            else {
                val head = list.head()
                if (weight(tree) > weight(head)) listOf(tree) + list
                else listOf(head) + insertOrdered(tree, list.tail())
            }


    /**
     * This function will be called in the following way:
     *
     *   until(singleton, combine)(trees)
     *
     * where `trees` is of type `List[CodeTree]`, `singleton` and `combine` refer to
     * the two functions defined above.
     *
     * In such an invocation, `until` should call the two functions until the list of
     * code trees contains only one single tree, and then return that singleton list.
     *
     * Hint: before writing the implementation,
     *  - start by defining the parameter types such that the above example invocation
     *    is valid. The parameter types of `until` should match the argument types of
     *    the example invocation. Also define the return type of the `until` function.
     *  - try to find sensible parameter names for `xxx`, `yyy` and `zzz`.
     */
    fun until(test: (List<CodeTree>) -> Boolean, funct: (List<CodeTree>) -> List<CodeTree>): (List<CodeTree>) -> List<CodeTree> = {
        input: List<CodeTree> ->
        repeatUntil(test, funct, input)
    }

    internal fun repeatUntil(test: (List<CodeTree>) -> Boolean, funct: (List<CodeTree>) -> List<CodeTree>, input: List<CodeTree>): List<CodeTree> = // No Currying in Kotlin
            if (test(input)) input
            else repeatUntil(test, funct, funct(input))


    /**
     * This function creates a code tree which is optimal to encode the text `chars`.
     *
     * The parameter `chars` is an arbitrary text. This function extracts the character
     * frequencies from that text and creates a code tree based on them.
     */
    fun createCodeTree(chars: List<Char>): CodeTree {
        val timesChar = times(chars)
        val orderedLeafList = makeOrderedLeafList(timesChar)
        val treeList = Huffman.until({ Huffman.singleton(it) }, { Huffman.combine(it) })(orderedLeafList)
        return treeList.head()
    }


    // Part 3: Decoding

    /**
     * This function decodes the bit sequence `bits` using the code tree `tree` and returns
     * the resulting list of characters.
     */
    fun decode(tree: CodeTree, bits: List<Int>): List<Char> = decodeLoop(tree, tree, bits)

    internal fun decodeLoop(fullTree: CodeTree, tree: CodeTree, bits: List<Int>): List<Char> =
            when (tree) {
                is Fork -> {
                    val bitsHead = bits.head()
                    if (bitsHead == 0)
                        decodeLoop(fullTree, tree.left, bits.tail())
                    else
                        decodeLoop(fullTree, tree.right, bits.tail())
                }
                is Leaf -> {
                    val result = listOf(tree.char)
                    if (bits.isEmpty()) result
                    else result + decodeLoop(fullTree, fullTree, bits)
                }
                else -> throw IllegalArgumentException("Unkown CodeTree subtype: ${tree.javaClass}")
            }


    /**
     * A Huffman coding tree for the French language.
     * Generated from the data given at
     *   http://fr.wikipedia.org/wiki/Fr%C3%A9quence_d%27apparition_des_lettres_en_fran%C3%A7ais
     */
    val frenchCode: CodeTree = Fork(Fork(Fork(Leaf('s', 121895), Fork(Leaf('d', 56269), Fork(Fork(Fork(Leaf('x', 5928), Leaf('j', 8351), listOf('x', 'j'), 14279), Leaf('f', 16351), listOf('x', 'j', 'f'), 30630), Fork(Fork(Fork(Fork(Leaf('z', 2093), Fork(Leaf('k', 745), Leaf('w', 1747), listOf('k', 'w'), 2492), listOf('z', 'k', 'w'), 4585), Leaf('y', 4725), listOf('z', 'k', 'w', 'y'), 9310), Leaf('h', 11298), listOf('z', 'k', 'w', 'y', 'h'), 20608), Leaf('q', 20889), listOf('z', 'k', 'w', 'y', 'h', 'q'), 41497), listOf('x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 72127), listOf('d', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 128396), listOf('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q'), 250291), Fork(Fork(Leaf('o', 82762), Leaf('l', 83668), listOf('o', 'l'), 166430), Fork(Fork(Leaf('m', 45521), Leaf('p', 46335), listOf('m', 'p'), 91856), Leaf('u', 96785), listOf('m', 'p', 'u'), 188641), listOf('o', 'l', 'm', 'p', 'u'), 355071), listOf('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u'), 605362), Fork(Fork(Fork(Leaf('r', 100500), Fork(Leaf('c', 50003), Fork(Leaf('v', 24975), Fork(Leaf('g', 13288), Leaf('b', 13822), listOf('g', 'b'), 27110), listOf('v', 'g', 'b'), 52085), listOf('c', 'v', 'g', 'b'), 102088), listOf('r', 'c', 'v', 'g', 'b'), 202588), Fork(Leaf('n', 108812), Leaf('t', 111103), listOf('n', 't'), 219915), listOf('r', 'c', 'v', 'g', 'b', 'n', 't'), 422503), Fork(Leaf('e', 225947), Fork(Leaf('i', 115465), Leaf('a', 117110), listOf('i', 'a'), 232575), listOf('e', 'i', 'a'), 458522), listOf('r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 881025), listOf('s', 'd', 'x', 'j', 'f', 'z', 'k', 'w', 'y', 'h', 'q', 'o', 'l', 'm', 'p', 'u', 'r', 'c', 'v', 'g', 'b', 'n', 't', 'e', 'i', 'a'), 1486387)

    /**
     * What does the secret message say? Can you decode it?
     * For the decoding use the `frenchCode' Huffman tree defined above.
     */
    val secret = listOf(0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1)

    /**
     * Write a function that returns the decoded secret
     */
    fun decodedSecret(): List<Char> = decode(frenchCode, secret)


    // Part 4a: Encoding using Huffman tree

    /**
     * This function encodes `text` using the code tree `tree`
     * into a sequence of bits.
     */
    fun encode(tree: CodeTree, text: List<Char>): List<Int> = encodeLoop(tree, tree, text)
    fun encodeLoop(fullTree: CodeTree, tree: CodeTree, text: List<Char>): List<Int> =
        if (text.isEmpty()) listOf<Int>()
        else {
            val c = text.head()
            when (tree) {
                is Fork ->
                    if (chars(tree.left).contains(c)) listOf(0) + encodeLoop(fullTree, tree.left, text)
                    else listOf(1) + encodeLoop(fullTree, tree.right, text)
                is Leaf -> encodeLoop(fullTree, fullTree, text.tail())
                else -> throw IllegalArgumentException("Unkown CodeTree subtype: ${tree.javaClass}")
            }
        }

    // Part 4b: Encoding using code table

    interface CodeTable: List<Pair<Char, List<Integer>>>

    /**
     * This function returns the bit sequence that represents the character `char` in
     * the code table `table`.
     */
    fun codeBits(table: CodeTable, char: Char): List<Integer> = TODO()

    /**
     * Given a code tree, create a code table which contains, for every character in the
     * code tree, the sequence of bits representing that character.
     *
     * Hint: think of a recursive solution: every sub-tree of the code tree `tree` is itself
     * a valid code tree that can be represented as a code table. Using the code tables of the
     * sub-trees, think of how to build the code table for the entire tree.
     */
    fun convert(tree: CodeTree): CodeTable = TODO()

    /**
     * This function takes two code tables and merges them into one. Depending on how you
     * use it in the `convert` method above, this merge method might also do some transformations
     * on the two parameter code tables.
     */
    fun mergeCodeTables(a: CodeTable, b: CodeTable): CodeTable = TODO()

    /**
     * This function encodes `text` according to the code tree `tree`.
     *
     * To speed up the encoding process, it first converts the code tree to a code table
     * and then uses it to perform the actual encoding.
     */
    fun quickEncode(tree: CodeTree, text: List<Char>): List<Integer> = TODO()

}
