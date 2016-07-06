import java.util.*

/**
 * A class to represent tweets.
 */
class Tweet(val user: String, val text: String, val retweets: Int)

/**
 * This represents a set of objects of type `Tweet` in the form of a binary search
 * tree. Every branch in the tree has two children (two `TweetSet`s). There is an
 * invariant which always holds: for every branch `b`, all elements in the left
 * subtree are smaller than the tweet at `b`. The elements in the right subtree are
 * larger.
 *
 * Note that the above structure requires us to be able to compare two tweets (we
 * need to be able to say which of two tweets is larger, or if they are equal). In
 * this implementation, the equality / order of tweets is based on the tweet's text
 * (see `def incl`). Hence, a `TweetSet` could not contain two tweets with the same
 * text from different users.
 *
 *
 * The advantage of representing sets as binary search trees is that the elements
 * of the set can be found quickly. If you want to learn more you can take a look
 * at the Wikipedia page [1], but this is not necessary in order to solve this
 * assignment.
 *
 * [1] http://en.wikipedia.org/wiki/Binary_search_tree
 */
abstract class TweetSet {

    /**
     * This method takes a predicate and returns a subset of all the elements
     * in the original set for which the predicate is true.
     *
     * Question: Can we implment this method here, or should it remain abstract
     * and be implemented in the subclasses?
     */
    fun filter(p: (Tweet) -> Boolean): TweetSet = this // TODO

    /**
     * This is a helper method for `filter` that propagetes the accumulated tweets.
     */
    abstract fun filterAcc(p: (Tweet) -> Boolean, acc: TweetSet): TweetSet

    /**
     * Returns a new `TweetSet` that is the union of `TweetSet`s `this` and `that`.
     *
     * Question: Should we implment this method here, or should it remain abstract
     * and be implemented in the subclasses?
     */
    fun union(that: TweetSet): TweetSet = this // TODO

    /**
     * Returns the tweet from this set which has the greatest retweet count.
     *
     * Calling `mostRetweeted` on an empty set should throw an exception of
     * type `java.util.NoSuchElementException`.
     *
     * Question: Should we implment this method here, or should it remain abstract
     * and be implemented in the subclasses?
     */
    fun mostRetweeted(): Tweet = Tweet("todo", "todo", 1) // TODO

    /**
     * Returns a list containing all tweets of this set, sorted by retweet count
     * in descending order. In other words, the head of the resulting list should
     * have the highest retweet count.
     *
     * Hint: the method `remove` on TweetSet will be very useful.
     * Question: Should we implment this method here, or should it remain abstract
     * and be implemented in the subclasses?
     */
    fun descendingByRetweet(): TweetList = Nil // TODO

    /**
     * The following methods are already implemented
     */

    /**
     * Returns a new `TweetSet` which contains all elements of this set, and the
     * the new element `tweet` in case it does not already exist in this set.
     *
     * If `this.contains(tweet)`, the current set is returned.
     */
    abstract fun incl(tweet: Tweet): TweetSet

    /**
     * Returns a new `TweetSet` which excludes `tweet`.
     */
    abstract fun remove(tweet: Tweet): TweetSet

    /**
     * Tests if `tweet` exists in this `TweetSet`.
     */
    abstract fun contains(tweet: Tweet): Boolean

    /**
     * This method takes a function and applies it to every element in the set.
     */
    abstract fun foreach(f: (Tweet) -> Unit): Unit
}

class Empty: TweetSet() {
    override fun filterAcc(p: (Tweet) -> Boolean, acc: TweetSet): TweetSet = Empty() // Todo

    /**
     * The following methods are already implemented
     */

    override fun contains(tweet: Tweet): Boolean = false

    override fun incl(tweet: Tweet): TweetSet = NonEmpty(tweet, Empty(), Empty())

    override fun remove(tweet: Tweet): TweetSet = this

    override fun foreach(f: (Tweet) -> Unit): Unit {}
}


class NonEmpty(val elem: Tweet, val left: TweetSet, val right: TweetSet): TweetSet() {

    override fun filterAcc(p: (Tweet) -> Boolean, acc: TweetSet): TweetSet = Empty() // TODO

    /**
     * The following methods are already implemented
     */

    override fun contains(x: Tweet): Boolean =
        if (x.text < elem.text) left.contains(x)
        else if (elem.text < x.text) right.contains(x)
             else true

    override fun incl(x: Tweet): TweetSet =
        if (x.text < elem.text) NonEmpty(elem, left.incl(x), right)
        else if (elem.text < x.text) NonEmpty(elem, left, right.incl(x))
             else this

    override fun remove(tw: Tweet): TweetSet =
        if (tw.text < elem.text) NonEmpty(elem, left.remove(tw), right)
        else if (elem.text < tw.text) NonEmpty(elem, left, right.remove(tw))
             else left.union(right)

    override fun foreach(f: (Tweet) -> Unit): Unit {
        f(elem)
        left.foreach(f)
        right.foreach(f)
    }
}


interface TweetList {
    val head: Tweet
    val tail: TweetList
    val isEmpty: Boolean
    fun foreach(f: (Tweet) -> Unit) {
        if (!isEmpty) {
            f(head)
            tail.foreach(f)
        }
    }
}

val Nil = object: TweetList {
    override val head = throw NoSuchElementException("head of EmptyList")
    override val tail = throw NoSuchElementException("tail of EmptyList")
    override val isEmpty = true
}

class Cons(override val head: Tweet, override val tail: TweetList): TweetList {
    override val isEmpty = false
}


object GoogleVsApple {
    val google = listOf("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
    val apple = listOf("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

    val googleTweets: TweetSet by lazy { Empty() } // TODO
    val appleTweets: TweetSet by lazy { Empty() } // TODO

    /**
     * A list of all tweets mentioning a keyword from either apple or google,
     * sorted by the number of retweets.
     */
    val trending: TweetList by lazy { Nil } // TODO
}

class Test {}

fun main(args : Array<String>) {
    GoogleVsApple.trending.foreach { println(it) }
}
