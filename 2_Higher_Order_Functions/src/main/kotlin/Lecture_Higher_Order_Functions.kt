// Higher Order Functions
//------------------------

fun sum(f: (Int) -> Int, a: Int, b: Int): Int =
    if (a > b) 0
    else f(a) + sum(f, a+1, b)

fun sumInts(a: Int, b: Int) = sum(::id, a, b)
fun sumCubes(a: Int, b: Int) = sum(::cube, a, b)
fun sumFactorials(a: Int, b: Int) = sum(::fact, a, b)

fun id(x: Int): Int = x
fun cube(x: Int): Int = x * x * x
fun fact(x: Int): Int = if (x == 0) 1 else x * fact(x-1)