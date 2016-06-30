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

// Using anonymous functions (closures)

fun sumInts2(a: Int, b: Int) = sum({it}, a, b)
fun sumCubes2(a: Int, b: Int) = sum({it*it*it}, a, b)
// Not posible for fact, because this is a recursive function

// Tail recursive sum

fun sum3(f: (Int) -> Int, a: Int, b: Int): Int = sum3Loop(f, a, b, 0)

private tailrec fun sum3Loop(f: (Int) -> Int, a: Int, b: Int, acc: Int): Int =
    if (a > b) acc
    else sum3Loop(f, a+1, b, acc+f(a))

fun sumInts3(a: Int, b: Int) = sum3({it}, a, b)
fun sumCubes3(a: Int, b: Int) = sum3({it*it*it}, a, b)
fun sumFactorials3(a: Int, b: Int) = sum3(::fact, a, b)

// Function as a result

fun createAdd(): (Int, Int)-> Int = { a, b -> a + b }

// Curried sum - Now sum returns a function which will iterate over the interval

fun sumCurried(f: (Int) -> Int): (Int, Int) -> Int = { a, b -> sum(f, a, b) }

val sumIntsCurried = sumCurried({ it })
val sumCubesCurried = sumCurried({ it * it * it })
val sumFactorialsCurried = sumCurried(::fact)

// Curried product

fun product(f: (Int) -> Int, a: Int, b: Int): Int =
        if (a > b) 1
        else f(a) * product(f, a+1, b)

fun productCurried(f: (Int) -> Int): (Int, Int) -> Int = { a, b -> product(f, a, b) }

// Factorial through product

val factorialThroughProduct = { x: Int -> product({it}, 1, x)}

// Generalized sum and product

fun mapReduce(f: (Int) -> Int, combine: (Int, Int) -> Int, initial: Int, a: Int, b: Int): Int =
        if (a > b) initial
        else combine(f(a),  mapReduce(f, combine, initial, a+1, b))

fun mapReduceCurried(f: (Int) -> Int, combine: (Int, Int) -> Int, initial: Int):  (Int, Int) -> Int = { a, b -> mapReduce(f, combine, initial, a, b) }