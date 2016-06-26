
/** Checks if parentheses are balanced in a string*/

fun balance(str: String) = balance(str.toList())

fun balance(chars: List<Char>): Boolean = balance(chars, 0)

fun balance(chars: List<Char>, opened: Int): Boolean =
        if (chars.isEmpty()) opened == 0
        else {
            when (chars.first()) {
                '(' -> balance(chars.tail(), opened+1)
                ')' -> when {
                    opened > 0 -> balance(chars.tail(), opened-1)
                    else -> false
                }
                else -> balance(chars.tail(), opened)
            }
        }

fun <T> List<T>.tail(): List<T> = subList(1, size)
