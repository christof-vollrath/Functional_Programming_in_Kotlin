package exercise_object_oriented_sets

import exercise_object_oriented_sets.TweetData
import exercise_object_oriented_sets.TweetReader
import org.junit.Assert.*
import org.junit.Test

class TweetReaderTest {

    @Test fun getTweetData_gizmodo() {
        val tweetData = TweetReader.getTweetData(TweetData.gizmodo)
        assertEquals(100, tweetData.size)
        assertEquals("gizmodo", tweetData[0].user)
        assertTrue(tweetData[0].text.startsWith("Kindle"))
        assertEquals(51, tweetData[0].retweets)
    }

    @Test fun allTweets_should_have_right_size() {
        var size = 0
        TweetReader.allTweets.foreach {  println(it); size++}
        println(size)
        assertEquals(300, size)
    }
}
