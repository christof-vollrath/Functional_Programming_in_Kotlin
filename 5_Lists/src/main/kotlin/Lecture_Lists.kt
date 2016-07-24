fun <T> pack(list: List<T>): List<List<T>> =
    if (list.isEmpty()) listOf<List<T>>()
    else if (list.size == 1) listOf(list)
    else {
        val head = list.first()
        val packedElement = list.takeWhile { it == head }
        val remainingList = list.subList(packedElement.size, list.size)
        listOf(packedElement) + pack(remainingList)
    }

fun <T> encode(list: List<T>): List<Pair<T, Int>> =
    pack(list).map{ Pair(it.first(), it.size)}
