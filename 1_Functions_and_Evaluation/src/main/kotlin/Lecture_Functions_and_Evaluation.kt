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
    if (abs(x) < 0.000001) 0.0
    else
        if (guessIsGoodEnough(guess, x)) guess
        else sqrt(x, improve(guess, x))

private fun guessIsGoodEnough(guess: Double, x: Double) =
    abs(guess * guess - x) / x < 0.000001

private fun improve(guess: Double, x: Double): Double  = (guess + x / guess) / 2
    // See: https://de.wikipedia.org/wiki/Newton-Verfahren#Berechnung_der_Quadratwurzel

/*


Tail recursion
Eucid's algorithm gcd greates common divisor
        def gcd(a: Int, b: Int): Int =
            if (b== 0) a else gcd(b, a % b)

Factorial
        def factorial(n: Int): Int =
            if (n == 0) 1 else n*factorial(n-1)

def factorial2(n: Int): Int = { // Tail recursive
    def loop(acc: Int, n: Int): Int =
    if (n == 0) acc else loop(acc*n, n-1)

    loop(1, n)
}
factorial2(3)
*/