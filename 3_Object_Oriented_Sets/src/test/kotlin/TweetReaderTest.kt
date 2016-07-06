import objsets.TweetData
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
}
