package exercise_object_oriented_sets

import org.junit.Assert.*
import org.junit.Test
import java.util.NoSuchElementException

class MoreTweetSetTest {
    // filter
    @Test fun filtering_an_empty_set_should_return_empty_set() {
        val set = Empty
        assertEquals(0, asSet(set.filter({true})).size)
    }
    @Test fun filtering_a_non_empty_set_with_false_should_return_empty_set() {
        val set = Empty.incl(Tweet("a", "Text a1", 1))
        assertEquals(0, asSet(set.filter({false})).size)
    }
    @Test fun filtering_one_element_set_with_true_should_return_set() {
        val set = Empty.incl(Tweet("a", "Text a1", 1))
        assertEquals(1, asSet(set.filter({true})).size)
    }
    @Test fun filtering_element_set_with_true_should_return_set() {
        val set = Empty.incl(Tweet("a", "Text a1", 1)).incl(Tweet("a", "Text a2", 2)).incl(Tweet("a", "Text a3", 3))
        assertEquals(3, asSet(set.filter({true})).size)
    }
    @Test fun filtering_element_set_with_condition_should_return_fulfiling_elements() {
        val set = Empty.incl(Tweet("a", "Text a1", 1)).incl(Tweet("a", "Text a2", 2)).incl(Tweet("a", "Text a3", 3))
        assertEquals(2, asSet(set.filter({it.retweets > 1})).size)
    }
    // union
    @Test fun union_of_two_empty_sets_should_be_empty_set() {
        val set1 = Empty
        val set2 = Empty
        val union = set1.union(set2)
        assertEquals(0, asSet(union).size)
    }
    @Test fun union_with_empty_set_should_be_original_set() {
        val set1 = Empty.incl(Tweet("a", "Text a1", 1))
        val set2 = Empty
        val union = set1.union(set2)
        assertEquals(1, asSet(union).size)
    }
    @Test fun union_of_empty_set_should_be_other_set() {
        val set1 = Empty
        val set2 = Empty.incl(Tweet("a", "Text a1", 1))
        val union = set1.union(set2)
        assertEquals(1, asSet(union).size)
    }
    @Test fun union_TweetSet() {
        val set1 = Empty.incl(Tweet("a", "Text a1", 1)).incl(Tweet("a", "Text a2", 2)).incl(Tweet("a", "Text a3", 2))
        val set2 = Empty.incl(Tweet("b", "Text b1", 1)).incl(Tweet("b", "Text b2", 2)).incl(Tweet("b", "Text b3", 2))
        val union = set1.union(set2)
        assertEquals(6, asSet(union).size)
    }
    @Test fun union_TweetSet_with_equal_texts() {
        val set1 = Empty.incl(Tweet("a", "Text a1", 1)).incl(Tweet("a", "Text a2", 2)).incl(Tweet("a", "Text a3", 2)).incl(Tweet("a", "Text", 2))
        val set2 = Empty.incl(Tweet("b", "Text", 2)).incl(Tweet("b", "Text b1", 1)).incl(Tweet("b", "Text b2", 2)).incl(Tweet("b", "Text b3", 2))
        val union = set1.union(set2)
        assertEquals(7, asSet(union).size)
    }
    // mostRetweeted
    @Test(expected=NoSuchElementException::class) fun mostRetweeted_on_empty_set_should_throw_excpetion() {
        Empty.mostRetweeted()
    }
    @Test fun mostRetweeted_for_one_element_should_return_this() {
        val set = Empty.incl(Tweet("a", "Text a1", 1))
        assertEquals(1, set.mostRetweeted().retweets)
    }
    @Test fun mostRetweeted_for_two_elements_should_return_the_right() {
        val set = Empty.incl(Tweet("a", "Text a1", 1)).incl(Tweet("a", "Text a2", 2))
        assertEquals(2, set.mostRetweeted().retweets)
    }
    @Test fun mostRetweeted_with_many_elements_should_return_the_right() {
        val set = Empty.incl(Tweet("a", "Text a1", 1)).incl(Tweet("a", "Text a9", 9))
                .incl(Tweet("a", "Text a2", 2)).incl(Tweet("a", "Text a3", 3))
                .incl(Tweet("a", "Text a7", 7)).incl(Tweet("a", "Text a5", 5))
        assertEquals(9, set.mostRetweeted().retweets)
    }
    // descendingByRetweet
    @Test fun descendingRetweet_for_empty_set_should_return_Nil() {
        assertEquals(Nil, Empty.descendingByRetweet())
    }
    @Test fun descendingRetweet_for_one_element_should_return_list_with_this() {
        val set = Empty.incl(Tweet("a", "Text a1", 1))
        assertEquals(1, set.descendingByRetweet().head.retweets)
        assertTrue(set.descendingByRetweet().tail.isEmpty)
    }
    @Test fun descendingRetweet_for_two_elements_should_return_sorted_list() {
        val set = Empty.incl(Tweet("a", "Text a1", 1)).incl(Tweet("a", "Text a2", 2))
        assertEquals(2, set.descendingByRetweet().head.retweets)
        assertEquals(1, set.descendingByRetweet().tail.head.retweets)
        assertTrue(set.descendingByRetweet().tail.tail.isEmpty)
    }
    // containsAny
    @Test fun containsAny_not_in_string() {
        val keywords = listOf("Alles", "Butter")
        assertFalse(containsAny("Anything else", keywords))
    }
    @Test fun containsAny_in_string() {
        val keywords = listOf("Alles", "else", "Butter")
        assertTrue(containsAny("Anything else", keywords))
    }
}
