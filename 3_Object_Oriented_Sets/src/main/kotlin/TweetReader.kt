import com.github.salomonbrys.kotson.*
import com.google.gson.Gson

object TweetReader {
    fun getTweetData(json: String): List<Tweet> =
            Gson().fromJson<List<Tweet>>(json)
}
