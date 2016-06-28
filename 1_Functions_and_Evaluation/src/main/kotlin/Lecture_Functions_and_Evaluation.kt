import java.lang.Math.*
/**
 * Kotlin doesn't have lazy parameter evaluation or call-by-name.
 * But this can be simulated through closures.
 */

tailrec fun loop(): Boolean = loop() // Don't call loop, it never terminates
// Without tailrec it would throw a stack overflow, but with tailrec is an endless loop

fun ignoreParameter(p: Boolean) {}
fun ignoreParameterWithClosure(p: ()->Boolean) {}

/**
 * && and || can be implemented as functions.
 * But second parameter must be call-by-name since it might not be evaluated
 */

inline fun conditionalAnd(p1: Boolean, p2: ()->Boolean): Boolean = if (p1) p2() else false
inline fun conditionalOr(p1: Boolean, p2: ()->Boolean): Boolean = if (p1) true else p2()

// Newton's method to calculate square root

fun sqrt(x: Double): Double = sqrt(x, 1.0)

private tailrec fun sqrt(x: Double, guess: Double) : Double =
    if (abs(x) < SQRT_DELTA) 0.0
    else
        if (guessIsGoodEnough(guess, x)) guess
        else sqrt(x, improve(guess, x))

private fun guessIsGoodEnough(guess: Double, x: Double) =
    abs(guess * guess - x) / x < SQRT_DELTA

private fun improve(guess: Double, x: Double): Double  = (guess + x / guess) / 2
    // See: https://de.wikipedia.org/wiki/Newton-Verfahren#Berechnung_der_Quadratwurzel

private val SQRT_DELTA = 0.00000001


// Tail recursion

// Eucid's algorithm gcd greatest common divisor
//-----------------------------------------------

fun gcd(a: Int, b: Int): Int {
    if (a == 0 || b == 0) throw IllegalArgumentException("Gcd can not be caluclated for 0")
    return calcGcd(a, b)
}

tailrec fun calcGcd(a: Int, b: Int): Int =
    if (b == 0) a else calcGcd(b, a % b)

// Factorial recursive and tail recursive

// Factorial is one of the classical examples for recursion
// but a very bad example for it.
// The most efficient implementation would be a table look up

// Factorial recursive

fun factorial(n: Int): Int =
    if (n == 0) 1 else n * factorial(n-1)

// Factorial tail recursive

fun factorial2(n: Int): Int =
    factorialLoop(1, n)

tailrec fun factorialLoop(acc: Int, n: Int): Int =
    if (n == 0) acc else factorialLoop(acc * n, n-1)

// Factorial iterative

fun factorial3(n: Int): Int {
    var res = 1
    for(i in 2..n) res *= i
    return res
}

fun createFactorialTable(): String {
    val tableBuilder = StringBuilder()
    (0..13).forEach {
        tableBuilder.append("        $it -> ${factorial(it)}\n")
    }
    return """fun factorial4(n: Int): Int = // Created by createFactorialTable
    when(n) {
${tableBuilder.toString()}
        else -> throw IllegalArgumentException("Number to big")
    }

"""
    }

fun factorial4(n: Int): Int = // Created by createFactorialTable
        when(n) {
            0 -> 1
            1 -> 1
            2 -> 2
            3 -> 6
            4 -> 24
            5 -> 120
            6 -> 720
            7 -> 5040
            8 -> 40320
            9 -> 362880
            10 -> 3628800
            11 -> 39916800
            12 -> 479001600
            13 -> 1932053504

            else -> throw IllegalArgumentException("Number to big")
        }
