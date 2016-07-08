package exercise_object_oriented_sets

import com.google.gson.Gson
import com.github.salomonbrys.kotson.*
import head
import tail

object TweetReader {
    fun getTweetData(json: String): List<Tweet> =
            Gson().fromJson<List<Tweet>>(json)

    private fun unionOfAllTweetSets(curSets: List<TweetSet>, acc: TweetSet): TweetSet =
        if (curSets.isEmpty()) acc
        else unionOfAllTweetSets(curSets.tail(), acc.union(curSets.head()))

    private fun toTweetSet(l: List<Tweet>): TweetSet =
        l.fold(Empty) { result: TweetSet, tweet: Tweet -> result.incl(tweet) }

    private val tweetSets = listOf(
//            toTweetSet(getTweetData(TweetData.gizmodo)),
//            toTweetSet(getTweetData(TweetData.TechCrunch)),
            toTweetSet(getTweetData(TweetData.engadget)),
            toTweetSet(getTweetData(TweetData.amazondeals))
    )
    val allTweets: TweetSet = unionOfAllTweetSets(tweetSets, Empty)

}
