package exercise_object_oriented_sets

import org.junit.Test
import java.util.*

class TweetSetTest {
    val set1 = Empty()
    val set2 = set1.incl(Tweet("a", "a body", 20))
    val set3 = set2.incl(Tweet("b", "b body", 20))
    val c = Tweet("c", "c body", 7)
    val d = Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)

    fun asSet(tweets: TweetSet): HashSet<Tweet> {
        var res = HashSet<Tweet>()
        tweets.foreach({res.add(it)})
        return res
    }

    fun size(set: TweetSet): Int = asSet(set).size

    @Test fun filter_on_empty_set() {
        assert(size(set1.filter({tw -> tw.user == "a"})) === 0)
    }

    @Test fun filter_a_on_set5() {
        assert(size(set5.filter({tw -> tw.user == "a"})) === 1)
    }

    @Test fun filter_20_on_set5() {
        assert(size(set5.filter({tw -> tw.retweets == 20})) === 2)
    }

    @Test fun union_set4c_and_set4d() {
        assert(size(set4c.union(set4d)) === 4)
    }

    @Test fun union_with_empty_set_1() {
            assert(size(set5.union(set1)) === 4)
    }

    @Test fun union_with_empty_set_2() {
        assert(size(set1.union(set5)) === 4)
    }

    @Test fun descending_set5() {
        val trends = set5.descendingByRetweet()
        assert(!trends.isEmpty)
        assert(trends.head.user == "a" || trends.head.user == "b")
    }

}
