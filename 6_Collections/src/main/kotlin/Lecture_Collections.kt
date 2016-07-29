import rx.Observable
import rx.lang.kotlin.observable
import java.util.LinkedList

// High-level test for prime

fun isPrime(i: Int): Boolean = ! (2..(i/2)).any { i % it == 0 }

// Given a positive integer n, final all pais of psoitive integers i and j, with 1 <= j < i < n such that i + j is prime.

fun pairsSumPrime(n: Int): List<Pair<Int, Int>> =
        (1..(n-1)).flatMap { i -> (1..(i-1)).map { j -> Pair(i,j)}}
                .filter { val (i, j) = it; isPrime(i+j) }

fun pairsSumPrime2(n: Int): List<Pair<Int, Int>> { // Using classic for loop
    val result = LinkedList<Pair<Int, Int>>()
    for (i in 1..(n - 1))
        for (j in 1..(i - 1))
            if (isPrime(i + j))
                result += Pair(i,j)
    return result
}

fun <T> Observable<T>.debug(s: String): Observable<T> = this.doOnNext({println("onNext $s: ${it} - [${Thread.currentThread().getName()}]")})

fun pairsSumPrime3(n: Int): List<Pair<Int, Int>> = // Using RxJava
    observable<Pair<Int, Int>> {
        for (i in 1..(n - 1))
            for (j in 1..(i - 1))
                it.onNext(Pair(i, j))
        it.onCompleted()
    }
    .debug("observable")
    .filter { val (i, j) = it; isPrime(i + j) }
    .debug("filtered")
    .toList()
    .debug("toList")
    .toBlocking().single()

fun pairsSumPrime4(n: Int): List<Pair<Int, Int>> = // Scala-Style for loop in kotlin
        LinkedList<Pair<Int, Int>>().apply {
        for (i in 1..(n - 1))
            for (j in 1..(i - 1))
                if (isPrime(i + j))
                    add(Pair(i,j))
    }

// N Queen problem
// One solution is a list of columns for each queen while every queen occupies one row
fun queens(n: Int): Set<List<Int>> =
    if (n == 0) setOf(emptyList<Int>())
    else queens(n-1).flatMap {
        solution -> extendSolution(n, solution)}.toSet()

internal fun extendSolution(n: Int, solution: List<Int>): Set<List<Int>> =
        (0..(n-1)).map { col -> Pair(col, solution) }
            .filter { val (col, solution) = it; isSafe(col, solution)}
            .map { val (col, solution) = it; solution + col}
            .toSet()

internal fun isSafe(c: Int, queens: List<Int>): Boolean {
    val r = queens.size
    val positionsWithRow = positionsWithRow(queens)
    return positionsWithRow.all {
        val (row, col) = it
        col != c && Math.abs(col - c) != r - row
    }
}

internal fun positionsWithRow(positions: List<Int>) =
        ((positions.size-1) downTo 0).zip(positions)