package exercise_object_oriented_sets

import com.google.gson.Gson
import com.github.salomonbrys.kotson.*

object TweetReader {
    fun getTweetData(json: String): List<Tweet> =
            Gson().fromJson<List<Tweet>>(json)

    val tweetLists = listOf(
            getTweetData(TweetData.gizmodo),
            getTweetData(TweetData.TechCrunch),
            getTweetData(TweetData.engadget),
            getTweetData(TweetData.amazondeals)
    )
    val allTweetsList = tweetLists.fold(listOf<Tweet>()) {
                list1, list2 ->
                list1 + list2
            }

    fun toTweetSet(l: List<Tweet>): TweetSet =
            l.fold(Empty) { result: TweetSet, tweet: Tweet -> result.incl(tweet) }

    val allTweets: TweetSet = toTweetSet(allTweetsList)
}

