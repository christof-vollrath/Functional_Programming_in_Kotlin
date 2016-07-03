import java.lang.Math.*

// Higher Order Functions
//------------------------

// 2.1 Higher Order Functions

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

// 2.2 Currying

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

// 2.3 Example: Finding Fixed Points

fun isCloseEnough(x: Double, y: Double) =
    abs((x - y) / x) / x < 0.0000001

fun fixedPoint(f: (Double) -> Double, guess: Double): Double {
    val next = f(guess)
    if (isCloseEnough(guess, next)) return next
    else return fixedPoint(f, next)
}

// Square root through fixed point
// x = y * y  => y = x / y, y as fixed point
// This would be an oszillating infinite loop
// which needs to be improved through averaging (average damp)
fun squareRootThroughFixedPoint(x: Double) =
    fixedPoint({y -> (y + x / y)/2.0}, 1.0)

fun averageDamp(f: (Double) -> Double) = {
    x: Double -> (f(x) + x) / 2.0
}

fun squareRootThroughFixedPointAndAverageDamp(x: Double) =
        fixedPoint(averageDamp({y -> x / y}), 1.0)

// 2.5 Functions and Data

class Rational(numerator: Int, denominator: Int) {
    val numerator: Int
    val denominator: Int

    init {
        val g = gcd(numerator, denominator)
        this.numerator = numerator / g
        this.denominator = denominator / g
    }

    operator fun plus(that: Rational) = Rational(numerator * that.denominator + that.numerator * denominator,
                                       denominator * that.denominator)
    operator fun unaryMinus() = Rational(-numerator,denominator)
    operator fun minus(that: Rational) = this + -that
    override fun toString(): String = "$numerator/$denominator"

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Rational

        if (numerator != other.numerator) return false
        if (denominator != other.denominator) return false

        return true
    }

    private tailrec fun gcd(a: Int, b: Int): Int =
        if (b == 0) a else gcd(b, a % b)

}
