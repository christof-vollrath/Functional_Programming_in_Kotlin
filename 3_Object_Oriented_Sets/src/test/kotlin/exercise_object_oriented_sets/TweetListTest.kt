package exercise_object_oriented_sets
import org.junit.Assert.*
import org.junit.Test

class TweetListTest {
    @Test fun create_TweetList_with_one_element() {
        val tweet = Tweet("a", "Test a", 1)
        val tweetList = Cons(tweet, Nil)
        assertEquals(tweet, tweetList.head)
        assertEquals(Nil, tweetList.tail)
    }
    @Test fun create_TweetList_with_two_elements() {
        val tweet1 = Tweet("a", "Test a", 1)
        val tweet2 = Tweet("b", "Test b", 1)
        val tweetList = Cons(tweet2, Cons(tweet1, Nil))
        assertEquals(tweet2, tweetList.head)
        assertEquals(tweet1, tweetList.tail.head)
        assertEquals(Nil, tweetList.tail.tail)
    }
}