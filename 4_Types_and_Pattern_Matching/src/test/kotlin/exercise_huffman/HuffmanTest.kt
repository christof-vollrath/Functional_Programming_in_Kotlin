package exercise_huffman

import org.junit.Test
import org.junit.Assert.*


class HuffManTest {
    @Test fun times_of_empty_list_should_be_empty() {
        assertTrue(Huffman.times(listOf<Char>()).isEmpty())
    }
    @Test fun times_of_list_with_one_char() {
        assertEquals(listOf(Pair('a', 1)), Huffman.times(listOf('a')))
    }
    @Test fun times_of_list_with_two_chars() {
        assertEquals(listOf(Pair('a', 1), Pair('b', 1)), Huffman.times(listOf('a', 'b')))
    }
    @Test fun times_of_list_with_three_chars() {
        assertEquals(listOf(Pair('a', 1), Pair('b', 1), Pair('c', 1)), Huffman.times(listOf('a', 'b', 'c')))
    }
    @Test fun times_of_list_with_repeating_chars() {
        assertEquals(listOf(Pair('a', 1), Pair('b', 2), Pair('c', 3)), Huffman.times(listOf('a', 'b', 'c', 'b', 'c', 'c')))
    }
    @Test fun mergeTimes_empty_list() {
        assertEquals(listOf(Pair('a', 1)), Huffman.mergeTimes('a', listOf<Pair<Char, Int>>()))
    }
}
