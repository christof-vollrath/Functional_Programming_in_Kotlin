import java.util.*

/** Counts the number of different changes for an amount */

fun countChange(money: Int, coins: List<Int>) = change(money, coins.sortedDescending()).filter{! it.isEmpty() }.size

/** Returns a list of all changes for a certain amount of money with given coin types
 * coin types must b sorted descending */
fun change(money: Int, coins: List<Int>): List<List<Int>> =
    when {
        money == 0 -> listOf(listOf())
        coins.isEmpty() -> emptyList()
        else -> {
            val result = LinkedList<List<Int>>()
            val fittingCoins = coins.filter{ it <= money }
            fittingCoins.forEach { fittingCoin ->
                val nextMoney = money - fittingCoin
                val nextCoins = coins.filter{ it <= fittingCoin } // Ignore coins too big, coins must be sorted descending
                change(nextMoney, nextCoins).forEach {
                    result += listOf(listOf(fittingCoin) + it)
                }
            }
            result
        }
    }
