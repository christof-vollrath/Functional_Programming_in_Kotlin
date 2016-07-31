package forcomp

import loadDictionary
import tail
import java.util.*

/** A word is simply a `String`. */
// type Word = String // No Type alias in Kotlin

/** A sentence is a `List` of words. */
// Sentence = List[Word] // No Type alias in Kotlin

/** `Occurrences` is a `List` of pairs of characters and positive integers saying
 *  how often the character appears.
 *  This list is sorted alphabetically w.r.t. to the character in each pair.
 *  All characters in the occurrence list are lowercase.
 *
 *  Any list of pairs of lowercase characters and their frequency which is not sorted
 *  is **not** an occurrence list.
 *
 *  Note: If the frequency of some character is zero, then that character should not be
 *  in the list.
 */
// type Occurrences = List[(Char, Int)] // No Type alias in Kotlin

/** The dictionary is simply a sequence of words.
 *  It is predefined and obtained as a sequence using the utility method `loadDictionary`.
 */
val dictionary: List<String> = loadDictionary()

/** Converts the word into its character occurrence list.
 *
 *  Note: the uppercase and lowercase version of the character are treated as the
 *  same character, and are represented as a lowercase character in the occurrence list.
 *
 *  Note: you must use `groupBy` to implement this method!
 */
fun wordOccurrences(w: String): List<Pair<Char, Int>> =
    LinkedHashMap<Char, Int>().apply {
        w.forEach {
            val c = it.toLowerCase()
            val occ = this[c] ?: 0
            this[c] = occ + 1
        }
    }.toList().sortedBy { val (c, n) = it; c }

/** Converts a sentence into its character occurrence list. */
fun sentenceOccurrences(s: List<String>): List<Pair<Char, Int>> =
        s.fold(LinkedHashMap<Char, Int>()) { map: MutableMap<Char, Int>, word: String -> map
            mergeOccurencesMap(map, wordOccurrences(word))
            map
        }.toList().sortedBy { val (c, n) = it; c }

internal fun mergeOccurencesMap(map: MutableMap<Char, Int>, wordOccurrences: List<Pair<Char, Int>>) =
    wordOccurrences.forEach {
        val (c, occ) = it
        val existingOcc = map[c] ?: 0
        map[c] = existingOcc + occ
    }

/** The `dictionaryByOccurrences` is a `Map` from different occurrences to a sequence of all
 *  the words that have that occurrence count.
 *  This map serves as an easy way to obtain all the anagrams of a word given its occurrence list.
 *
 *  For example, the word "eat" has the following character occurrence list:
 *
 *     `List(('a', 1), ('e', 1), ('t', 1))`
 *
 *  Incidentally, so do the words "ate" and "tea".
 *
 *  This means that the `dictionaryByOccurrences` map will contain an entry:
 *
 *    List(('a', 1), ('e', 1), ('t', 1)) -> Seq("ate", "eat", "tea")
 *
 */
val dictionaryByOccurrences: Map<List<Pair<Char, Int>>, List<String>> by lazy {
    createDictionaryByOccurences(dictionary)
}

fun createDictionaryByOccurences(dict: List<String>): HashMap<List<Pair<Char, Int>>, List<String>> {
    return HashMap<List<Pair<Char, Int>>, List<String>>().apply {
        dict.forEach { word ->
            val occ = wordOccurrences(word)
            val existingWords = this[occ] ?: LinkedList<String>()
            this[occ] = existingWords + word
        }
    }
}

/** Returns all the anagrams of a given word. */
fun wordAnagrams(word: String): List<String> = dictionaryByOccurrences[wordOccurrences(word)] ?: emptyList()

/* Similar to combinations but without nr of occuences */
fun <T> simpleCombinations(l: List<T>): List<List<T>> =
    if (l.isEmpty()) listOf(emptyList())
    else {
        val first = l.first()
        val tail = l.tail()
        val tailCombinations = simpleCombinations(tail)
        tailCombinations + tailCombinations.map { listOf(first) + it }
    }

/** Returns the list of all subsets of the occurrence list.
 *  This includes the occurrence itself, i.e. `List(('k', 1), ('o', 1))`
 *  is a subset of `List(('k', 1), ('o', 1))`.
 *  It also include the empty subset `List()`.
 *
 *  Example: the subsets of the occurrence list `List(('a', 2), ('b', 2))` are:
 *
 *    List(
 *      List(),
 *      List(('a', 1)),
 *      List(('a', 2)),
 *      List(('b', 1)),
 *      List(('a', 1), ('b', 1)),
 *      List(('a', 2), ('b', 1)),
 *      List(('b', 2)),
 *      List(('a', 1), ('b', 2)),
 *      List(('a', 2), ('b', 2))
 *    )
 *
 *  Note that the order of the occurrence list subsets does not matter -- the subsets
 *  in the example above could have been displayed in some other order.
 */
fun combinations(occurrences: List<Pair<Char, Int>>): List<List<Pair<Char, Int>>> =
    if (occurrences.isEmpty()) listOf(emptyList<Pair<Char, Int>>())
    else {
        val first = occurrences.first()
        val tail = occurrences.tail()
        val tailCombinations = combinations(tail)
        val (c, n) = first
        LinkedList(tailCombinations).apply {
            for(i in 1..n)
                this.addAll(tailCombinations.map{ listOf(Pair(c,i)) + it })
        }
    }

/** Subtracts occurrence list `y` from occurrence list `x`.
 *
 *  The precondition is that the occurrence list `y` is a subset of
 *  the occurrence list `x` -- any character appearing in `y` must
 *  appear in `x`, and its frequency in `y` must be smaller or equal
 *  than its frequency in `x`.
 *
 *  Note: the resulting value is an occurrence - meaning it is sorted
 *  and has no zero-entries.
 */
fun subtract(x: List<Pair<Char, Int>>, y: List<Pair<Char, Int>>): List<Pair<Char, Int>> =
    if (x.isEmpty()) emptyList()
    else {
        val subtractMap = HashMap<Char, Int>(); subtractMap.putAll(y)
        LinkedList<Pair<Char, Int>>().apply {
            x.forEach {
                val (c, n) = it
                val subtr = n - (subtractMap[c] ?: 0)
                if (subtr > 0) this += Pair(c, subtr)
            }
        }
    }

/** Returns a list of all anagram sentences of the given sentence.
 *
 *  An anagram of a sentence is formed by taking the occurrences of all the characters of
 *  all the words in the sentence, and producing all possible combinations of words with those characters,
 *  such that the words have to be from the dictionary.
 *
 *  The number of words in the sentence and its anagrams does not have to correspond.
 *  For example, the sentence `List("I", "love", "you")` is an anagram of the sentence `List("You", "olive")`.
 *
 *  Also, two sentences with the same words but in a different order are considered two different anagrams.
 *  For example, sentences `List("You", "olive")` and `List("olive", "you")` are different anagrams of
 *  `List("I", "love", "you")`.
 *
 *  Here is a full example of a sentence `List("Yes", "man")` and its anagrams for our dictionary:
 *
 *    List(
 *      List(en, as, my),
 *      List(en, my, as),
 *      List(man, yes),
 *      List(men, say),
 *      List(as, en, my),
 *      List(as, my, en),
 *      List(sane, my),
 *      List(Sean, my),
 *      List(my, en, as),
 *      List(my, as, en),
 *      List(my, sane),
 *      List(my, Sean),
 *      List(say, men),
 *      List(yes, man)
 *    )
 *
 *  The different sentences do not have to be output in the order shown above - any order is fine as long as
 *  all the anagrams are there. Every returned word has to exist in the dictionary.
 *
 *  Note: in case that the words of the sentence are in the dictionary, then the sentence is the anagram of itself,
 *  so it has to be returned in this list.
 *
 *  Note: There is only one anagram of an empty sentence.
 */
fun sentenceAnagrams(sentence: List<String>): List<List<String>> = occurrencesAnagrams(sentenceOccurrences(sentence))

fun occurrencesAnagrams(occurrences: List<Pair<Char, Int>>): List<List<String>> =
    if (occurrences.isEmpty()) listOf(emptyList())
    else LinkedList<List<String>>().apply {
        combinations(occurrences).forEach { firstOccurrences ->
            val wordList = dictionaryByOccurrences[firstOccurrences]
            if (wordList != null && wordList.size > 0) {
                val nextOccurrences = subtract(occurrences, firstOccurrences)
                wordList.forEach() { word ->
                    if (nextOccurrences.isEmpty())
                        this.add(listOf(word))
                    else {
                        val nextAnagrams = occurrencesAnagrams(nextOccurrences)
                        if (!nextAnagrams.isEmpty()) { // If nextAnagrams is empty, no anagram found for the rest, we have to look for something else
                            nextAnagrams.forEach {
                                this.add(listOf(word) + it)
                            }
                        }
                    }
                }
            }
        }
    }

