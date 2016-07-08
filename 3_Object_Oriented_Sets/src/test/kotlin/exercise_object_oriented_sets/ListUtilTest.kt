package exercise_object_oriented_sets

import head
import org.junit.Assert.*
import org.junit.Test
import tail

class ListUtilTest {
    @Test(expected = IndexOutOfBoundsException::class) fun head_empty_list_should_throw_exception() {
        listOf<Integer>().head()
    }
    @Test fun head_list_with_one_element_should_return_element() {
        assertEquals(1, listOf(1).head())
    }
    @Test fun head_list_with_two_elements_should_return_first_element() {
        assertEquals(10, listOf(10,20).head())
    }
    @Test(expected = IndexOutOfBoundsException::class) fun tail_empty_list_should_throw_exception() {
        assertTrue(listOf<Integer>().tail().isEmpty())
    }
    @Test fun tail_list_with_one_element_should_return_empty_list() {
        assertTrue(listOf(1).tail().isEmpty())
    }
    @Test fun tail_list_with_three_elements_should_return_last_two_element() {
        assertEquals(listOf(20,30), listOf(10,20,30).tail())
    }
}

