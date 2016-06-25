
/** Checks if parentheses are balanced */

fun balance(str: String) = balance(str.toList())

fun balance(chars: List<Char>): Boolean = balance(chars, 0)

fun balance(chars: List<Char>, opened: Int): Boolean =
        when {
            chars.isEmpty() -> when {
                opened == 0 -> true
                else -> false
            }
            else -> {
                when (chars.first()) {
                    '(' -> balance(chars.tail(), opened+1)
                    ')' -> when {
                        opened > 0 -> balance(chars.tail(), opened-1)
                        else -> false
                    }
                    else -> balance(chars.tail(), opened)
                }
            }
        }

fun <T> List<T>.tail(): List<T> = subList(1, size)
