// Functional Sets
// ---------------

// Sets defined through functions

// Kotlin 1.0 doesn't yet have type aliases, therefore a data class is needed to hold the type

data class FunctionalSet(val check: (Int) -> Boolean )

val emptySet = FunctionalSet({false})

fun contains(functionalSet: FunctionalSet, i: Int): Boolean = functionalSet.check(i)

// 2.1 Basic Functions on Sets

fun singletonSet(i: Int): FunctionalSet = FunctionalSet({it == i})

fun union(set1: FunctionalSet, set2: FunctionalSet): FunctionalSet = FunctionalSet({ contains(set1, it) || contains(set2, it) })

fun intersect(set1: FunctionalSet, set2: FunctionalSet): FunctionalSet = FunctionalSet({ contains(set1, it) && contains(set2, it) })

fun diff(set1: FunctionalSet, set2: FunctionalSet): FunctionalSet = FunctionalSet({ contains(set1, it) && ! contains(set2, it)})

// 2.2 Queries and Transformations on Sets

fun forall(set: FunctionalSet, function: (Int) -> Boolean): Boolean =
    inforall(set, function, -1000, 1000) // Here, we consider that an integer x has the property -1000 <= x <= 1000 in order to limit the search space

private fun inforall(set: FunctionalSet, function: (Int) -> Boolean, a: Int, b: Int): Boolean =
    if (a > b) true
    else
        if (contains(set, a) && !function(a)) false
        else inforall(set, function, a+1, b)

fun exists(set: FunctionalSet, function: (Int) -> Boolean): Boolean = ! forall(set, {a: Int -> ! function(a) })

fun map(set: FunctionalSet, function: (Int) -> Int): FunctionalSet =
    inmap(set, function, -1000, 1000)

fun inmap(set: FunctionalSet, function: (Int) -> Int, a: Int, b: Int): FunctionalSet =
    if (a > b) emptySet
    else
        if (contains(set, a)) union(singletonSet(function(a)), inmap(set, function, a+1, b))
        else inmap(set, function, a+1, b)


