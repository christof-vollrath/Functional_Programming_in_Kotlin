
import org.junit.Test
import org.junit.Assert.*

class Lecture_Higher_Order_FunctionsTest {

    // 2.1 Higher Order Functions

    @Test fun sumInts_1_3_should_be_6() {
        assertEquals(6, sumInts(1,3))
    }

    @Test fun sumCubes_1_3_should_be_36() {
        assertEquals(36, sumCubes(1,3))
    }

    @Test fun sumFactorials_1_3_should_be_9() {
        assertEquals(9, sumFactorials(1,3))
    }

    // Using anonymous functions (closures)

    @Test fun sumInts2_1_3_should_be_6() {
        assertEquals(6, sumInts2(1,3))
    }

    @Test fun sumCube2_1_3_should_be_36() {
        assertEquals(36, sumCubes2(1,3))
    }

    // Tail recursive sum

    @Test fun sumInts3_1_3_should_be_6() {
        assertEquals(6, sumInts3(1,3))
    }

    @Test fun sumCubes3_1_3_should_be_36() {
        assertEquals(36, sumCubes3(1,3))
    }

    @Test fun sumFactorials3_1_3_should_be_9() {
        assertEquals(9, sumFactorials3(1,3))
    }

    // Function as result

    @Test fun createAdd_1_2_should_be_3() {
        assertEquals(3, createAdd()(1,2))
    }

    // Curried sum -- now sum returns a function which will iterate over the interval

    @Test fun sumIntsCurried_1_3_should_be_6() {
        assertEquals(6, sumIntsCurried(1,3))
    }

    @Test fun sumCubesCurried_1_3_should_be_36() {
        assertEquals(36, sumCubesCurried(1,3))
    }

    @Test fun sumFactorialsCurried_1_3_should_be_9() {
        assertEquals(9, sumFactorialsCurried(1,3))
    }

    // Curried sum - avoiding the middleman

    @Test fun sumCurried_Ints_1_3_should_be_6() {
        assertEquals(6, sumCurried { it } (1,3))
    }

    @Test fun sumCurried_Cubes_1_3_should_be_36() {
        assertEquals(36, sumCurried { it*it*it} (1,3))
    }

    @Test fun sumCurried_Factorials_1_3_should_be_9() {
        assertEquals(9, sumCurried(::fact)(1,3))
    }

    // Curried product

    @Test fun productCurried_Ints_1_3_should_be_6() {
        assertEquals(6, productCurried { it } (1,3))
    }

    @Test fun productCurried_Cubes_1_3_should_be_216() {
        assertEquals(216, productCurried { it*it*it} (1,3))
    }

    @Test fun productCurried_Factorials_1_3_should_be_12() {
        assertEquals(12, productCurried(::fact)(1,3))
    }

    @Test fun productCurried_Square_3_7_should_be_6350400() {
        assertEquals(6350400,productCurried({it*it})(3, 7))
    }

    // Factorial through product

    @Test fun factorialThroughProduct_5_should_be_120() {
        assertEquals(120, factorialThroughProduct(5))
    }


    // Generalized sum and product

    @Test fun mapReduce_sumCubes_1_3_should_be_36() {
        assertEquals(36, mapReduce({it*it*it}, { a: Int, b: Int -> a + b}, 0, 1, 3))
    }

    @Test fun mapReduce_productFactorials_1_3_should_be_12() {
        assertEquals(12, mapReduce(::fact, { a: Int, b: Int -> a * b}, 1, 1, 3))
    }

    @Test fun mapReduceCurried_sumCubes_1_3_should_be_36() {
        assertEquals(36, mapReduceCurried({it*it*it}, { a: Int, b: Int -> a + b}, 0)( 1, 3))
    }

    @Test fun mapReduceCurried_productFactorials_1_3_should_be_12() {
        assertEquals(12, mapReduceCurried(::fact, { a: Int, b: Int -> a * b}, 1)( 1, 3))
    }


    // 2.3 Example: Finding Fixed Points

    @Test fun fixedpoint_should_be_2() {
        assertEquals(2.0, fixedPoint({ x -> 1 + x / 2.0 }, 1.0), DELTA)
    }


    @Test fun square_root_as_fixed_point() {
        assertEquals(2.0, squareRootThroughFixedPoint(4.0), DELTA)
    }

    @Test fun square_root_as_fixed_point_of_average_damp() {
        assertEquals(2.0, squareRootThroughFixedPointAndAverageDamp(4.0), DELTA)
    }

    val DELTA = 0.0001


    // 2.5 Functions and Data

    @Test fun add_rationals() {
        assertEquals(Rational(7,6), Rational(1,2) + (Rational(2,3)))
    }

    @Test fun print_rationals() {
        assertEquals("7/6", Rational(7,6).toString())
    }

    @Test fun negate_rationals() {
        assertEquals(Rational(-7,6), -Rational(7,6))
    }

    @Test fun x_sub_y_sub_z() {
        assertEquals(Rational(-79,42), Rational(1,3) - Rational(5,7) - Rational(3,2))
    }

    @Test fun rationals_should_be_simplified() {
        assertEquals("1/2", Rational(5,10).toString())
        assertEquals(Rational(3,2), Rational(6,4))
    }


}
