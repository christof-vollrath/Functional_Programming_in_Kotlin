/** Creates Pasccal's triangle
 *  Like
 * 1
 * 1  1
 * 1  2  1
 * 1  3  3  1
 * 1  4  6  4  1

 * */

fun pascal(column: Int, row: Int): Int =
    if (row == 0)
        if (column == 0) 1
        else 0
    else pascal(column-1, row-1) + pascal(column, row-1)

fun printPascal(column: Int, row: Int): String {
    val result = StringBuilder()
    for(r in 0..row) {
        for (c in 0..column) {
            val p = pascal(c, r)
            result.append(if (p == 0) "   " else " ${p} ")
        }
        result.append('\n')
    }
    return result.toString()
}

