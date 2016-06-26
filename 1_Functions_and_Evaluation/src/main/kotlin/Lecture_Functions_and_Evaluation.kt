//TODO Delayed Evaluation in Kotlin using closures

// TODO def and(x,y) = x && y // without &&
def and(x: Boolean, y: => Boolean) = if (x) y else false
def and(x: Boolean, y: => Boolean) = if (x) y else false
// TODO def or(x,y) = y || y
//TODO Newton Method
def abs(x:Double) = if (x < 0) -x else x

def sqrt(x: Double) = {
    def sqrtIter(guess: Double, x: Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)

    def isGoodEnough(guess: Double, x: Double) = abs(guess * guess -x) / x < 0.001

    def improve(guess: Double, x: Double) =(guess + x / guess) / 2

    sqrtIter(1.0, x)
}
Nesting functions in functions


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