import kotlin.collections.List

fun<T> List<T>.head(): T = get(0)
fun<T> List<T>.tail(): List<T> = subList(1, size)
