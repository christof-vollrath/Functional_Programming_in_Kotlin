package exercise_object_oriented_sets

import com.google.gson.Gson
import com.github.salomonbrys.kotson.*

object TweetReader {
    fun getTweetData(json: String): List<Tweet> =
            Gson().fromJson<List<Tweet>>(json)

    private fun unionOfAllTweetSets(curSets: List<TweetSet>, acc: TweetSet): TweetSet =
        if (curSets.isEmpty()) acc
        else unionOfAllTweetSets(curSets.takeLast(curSets.size-1), acc.union(curSets.get(0)))

    private fun toTweetSet(l: List<Tweet>): TweetSet =
        l.fold(Empty()) { result: TweetSet, tweet: Tweet -> result.incl(tweet) }

    private val tweetSets = listOf(
            toTweetSet(getTweetData(TweetData.gizmodo))
    )
    val allTweets: TweetSet = unionOfAllTweetSets(tweetSets, Empty())

}
