package exercise_huffman

import org.junit.Test
import org.junit.Assert.*
import tail

class HuffManTest {

    // times
    @Test fun times_of_empty_list_should_be_empty() {
        assertTrue(Huffman.times(listOf<Char>()).isEmpty())
    }
    @Test fun times_of_list_with_one_char() {
        assertEquals(listOf(Pair('a', 1)), Huffman.times(listOf('a')))
    }
    @Test fun times_of_list_with_two_chars() {
        assertEquals(listOf(Pair('b', 1), Pair('a', 1)), Huffman.times(listOf('a', 'b')))
    }
    @Test fun times_of_list_with_three_chars() {
        assertEquals(listOf(Pair('c', 1), Pair('b', 1), Pair('a', 1)), Huffman.times(listOf('a', 'b', 'c')))
    }
    @Test fun times_of_list_with_repeating_chars() {
        assertEquals(listOf(Pair('c', 3), Pair('b', 2), Pair('a', 1)), Huffman.times(listOf('a', 'b', 'c', 'b', 'c', 'c')))
    }
    @Test fun mergeTimes_empty_list() {
        assertEquals(listOf(Pair('a', 1)), Huffman.mergeTimes('a', listOf<Pair<Char, Int>>()))
    }
    @Test fun mergeTimes_list_with_other_chars() {
        assertEquals(listOf(Pair('b', 2), Pair('c', 3), Pair('a', 1)), Huffman.mergeTimes('a', listOf(Pair('b', 2), Pair('c', 3))))
    }

    // makeOrderedLeafList
    @Test fun makeOrderedLeafList_on_empty_list() {
        assertTrue(Huffman.makeOrderedLeafList(listOf<Pair<Char, Int>>()).isEmpty())
    }
    @Test fun makeOrderedLeafList_on_list_with_one_element() {
        assertEquals(listOf(Huffman.Leaf('a', 1)), Huffman.makeOrderedLeafList(listOf(Pair('a', 1))))
    }
    @Test fun makeOrderedLeafList_on_list_with_two_elements() {
        assertEquals(listOf(Huffman.Leaf('b', 2), Huffman.Leaf('a', 1)), Huffman.makeOrderedLeafList(listOf(Pair('a', 1), Pair('b', 2))))
    }
    @Test fun makeOrderedLeafList_on_list_with_some_elements() {
        assertEquals(listOf(Huffman.Leaf('c', 50), Huffman.Leaf('a', 10), Huffman.Leaf('d', 3), Huffman.Leaf('b', 2)), Huffman.makeOrderedLeafList(listOf(Pair('a', 10), Pair('b', 2), Pair('c', 50), Pair('d', 3))))
    }
    @Test fun highestLeaf_on_list_with_two_elements() {
        assertEquals(Huffman.Leaf('b', 2), Huffman.highestLeaf(listOf(Pair('a', 1), Pair('b', 2))))
    }
    @Test fun removeTimes_on_empty_list() {
        assertTrue(Huffman.removeTimes('a', listOf<Pair<Char, Int>>()).isEmpty())
    }
    @Test fun removeTimes_on_list_with_one_element_not_matching() {
        assertEquals(listOf(Pair('b', 1)), Huffman.removeTimes('a', listOf(Pair('b', 1))))
    }
    @Test fun removeTimes_on_list_with_one_element_matching() {
        assertTrue(Huffman.removeTimes('b', listOf(Pair('b', 1))).isEmpty())
    }
    @Test fun removeTimes_on_list_with_some_elements() {
        assertEquals(listOf(Pair('a', 1), Pair('c', 3)), Huffman.removeTimes('b', listOf(Pair('a', 1), Pair('b', 2), Pair('c', 3))))
    }

    // singleton
    @Test fun singleton_on_list_with_one_tree() {
        assertTrue(Huffman.singleton(listOf(Huffman.Leaf('a', 1))))
    }
    @Test fun singleton_on_list_with_two_trees() {
        assertFalse(Huffman.singleton(listOf(Huffman.Leaf('a', 1), Huffman.Leaf('b', 1))))
    }

    // combine
    @Test fun combine_empty_list_should_return_empty_list() {
        assertTrue(Huffman.combine(listOf<Huffman.CodeTree>()).isEmpty())
    }
    @Test fun combine_list_with_one_tree_should_return_this_list() {
        assertEquals(listOf(Huffman.Leaf('a', 1)), Huffman.combine(listOf(Huffman.Leaf('a', 1))))
    }
    @Test fun combine_list_with_two_leaves_should_combine_them_1() {
        assertEquals(listOf(Huffman.Fork(Huffman.Leaf('a', 1), Huffman.Leaf('b', 2), listOf('a','b'), 3)),
                     Huffman.combine(listOf(Huffman.Leaf('a', 1), Huffman.Leaf('b', 2))))
    }
    @Test fun combine_list_with_two_leaves_should_combine_them_2() {
        assertEquals(listOf(Huffman.Fork(Huffman.Leaf('a', 1), Huffman.Leaf('b', 2), listOf('a','b'), 3)),
                Huffman.combine(listOf(Huffman.Leaf('b', 2), Huffman.Leaf('a', 1))))
    }
    @Test fun combine_list_with_three_leaves_should_combine_them_2_and_insert_before() {
        assertEquals(listOf(Huffman.Fork(Huffman.Leaf('a', 1), Huffman.Leaf('b', 2), listOf('a','b'), 3), Huffman.Leaf('c', 4)),
                Huffman.combine(listOf(Huffman.Leaf('b', 2), Huffman.Leaf('a', 1), Huffman.Leaf('c', 4))))
    }
    @Test fun combine_list_with_three_leaves_should_combine_them_2_and_insert_after() {
        assertEquals(listOf(Huffman.Leaf('c', 4), Huffman.Fork(Huffman.Leaf('a', 2), Huffman.Leaf('b', 3), listOf('a','b'), 5)),
                Huffman.combine(listOf(Huffman.Leaf('b', 3), Huffman.Leaf('a', 2), Huffman.Leaf('c', 4))))
    }
    // insertOrdered
    @Test fun insertOrdered_empty_list_should_return_list_with_element() {
        assertEquals(listOf(Huffman.Leaf('a', 1)), Huffman.insertOrdered(Huffman.Leaf('a', 1), listOf<Huffman.CodeTree>()))
    }
    @Test fun insertOrdered_list_with_bigger_element() {
        assertEquals(listOf(Huffman.Leaf('a', 1),Huffman.Leaf('b', 2)), Huffman.insertOrdered(Huffman.Leaf('a', 1), listOf(Huffman.Leaf('b', 2))))
    }
    @Test fun insertOrdered_list_with_smaller_element() {
        assertEquals(listOf(Huffman.Leaf('a', 1),Huffman.Leaf('b', 2)), Huffman.insertOrdered(Huffman.Leaf('b', 2), listOf(Huffman.Leaf('a', 1))))
    }

    // until
    @Test fun remove_until_empty_for_empty_list() {
        assertTrue(Huffman.until({ it.isEmpty()}, { it.tail() })(listOf<Huffman.CodeTree>()).isEmpty())
    }
    @Test fun remove_until_empty_for_list() {
        assertTrue(Huffman.until({ it.isEmpty()}, { it.tail() })(listOf(Huffman.Leaf('b', 2), Huffman.Leaf('a', 1))).isEmpty())
    }
    @Test fun combine_until_singleton_1() {
        assertEquals(
            listOf(
                Huffman.Fork(
                    Huffman.Leaf('c', 4),
                    Huffman.Fork(Huffman.Leaf('a', 2), Huffman.Leaf('b', 3), listOf('a','b'), 5),
                    listOf('c', 'a', 'b'), 9)),
                Huffman.until({ Huffman.singleton(it) }, { Huffman.combine(it) })(
                    listOf(Huffman.Leaf('b', 3), Huffman.Leaf('a', 2), Huffman.Leaf('c', 4))
                )
        )
    }
    @Test fun combine_until_singleton_2() {
        assertEquals(
            listOf(
                Huffman.Fork(
                    Huffman.Fork(Huffman.Leaf('a', 2), Huffman.Leaf('b', 3), listOf('a','b'), 5),
                    Huffman.Leaf('c', 6),
                    listOf('a', 'b', 'c'), 11)),
            Huffman.until({ Huffman.singleton(it) }, { Huffman.combine(it) })(
                listOf(Huffman.Leaf('b', 3), Huffman.Leaf('a', 2), Huffman.Leaf('c', 6))
            )
        )
    }
    @Test fun combine_until_example_from_exercise() {
        assertEquals(
            listOf(
                Huffman.Fork(
                    Huffman.Leaf('A', 8),
                    Huffman.Fork(
                        Huffman.Fork(
                                Huffman.Leaf('B', 3),
                                Huffman.Fork(
                                    Huffman.Leaf('C', 1), Huffman.Leaf('D', 1), listOf('C', 'D'), 2
                                ),
                                listOf('B', 'C', 'D'), 5
                        ),
                        Huffman.Fork(
                            Huffman.Fork(
                                Huffman.Leaf('E', 1), Huffman.Leaf('F', 1), listOf('E', 'F'), 2
                            ),
                            Huffman.Fork(
                                Huffman.Leaf('G', 1), Huffman.Leaf('H', 1), listOf('G', 'H'), 2
                            ),
                                listOf('E', 'F', 'G', 'H'), 4
                        ),
                        listOf('B', 'C', 'D', 'E', 'F', 'G', 'H'), 9
                    ),
                    listOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'), 17
                )
            ),
            Huffman.until({ Huffman.singleton(it) }, { Huffman.combine(it) })(
                listOf(Huffman.Leaf('B', 3), Huffman.Leaf('A', 8),
                       Huffman.Leaf('F', 1), Huffman.Leaf('G', 1), Huffman.Leaf('H', 1),
                       Huffman.Leaf('C', 1), Huffman.Leaf('D', 1), Huffman.Leaf('E', 1)

                )
        )

        )
    }
}
