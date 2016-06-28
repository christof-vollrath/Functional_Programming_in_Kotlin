
import org.junit.Test
import org.junit.Assert.*
import org.junit.Ignore

// Tail recursion

class Functions_and_EvaluationTest_5_factorial {

    // Factorial recursive and tail recursive
    //-----------------------------------------------

    @Test fun factorial_1_should_be_1() {
        assertEquals(1, factorial(1))
    }

    @Test fun factorial_2_should_be_2() {
        assertEquals(2, factorial(2))
    }

    @Test fun factorial_3_should_be_6() {
        assertEquals(6, factorial(3))
    }

    @Test fun factorial_10_should_be_3628800() {
        assertEquals(3628800, factorial(10))
    }

    @Test fun factorial2_1_should_be_1() {
        assertEquals(1, factorial(1))
    }

    @Test fun factorial2_2_should_be_2() {
        assertEquals(2, factorial(2))
    }

    @Test fun factorial2_3_should_be_6() {
        assertEquals(6, factorial(3))
    }

    @Test fun factorial2_10_should_be_3628800() {
        assertEquals(3628800, factorial(10))
    }

    @Test fun factorial3_1_should_be_1() {
        assertEquals(1, factorial(1))
    }

    @Test fun factorial3_2_should_be_2() {
        assertEquals(2, factorial(2))
    }

    @Test fun factorial3_3_should_be_6() {
        assertEquals(6, factorial(3))
    }

    @Test fun factorial3_10_should_be_3628800() {
        assertEquals(3628800, factorial(10))
    }

    @Test fun createFactorialTable_should_create_fun() {
        val createFun = createFactorialTable()
        println(createFun)
        assertTrue(createFun.startsWith("fun"))
        assertEquals(15, Regex("->").findAll(createFun).count())
    }

    @Test fun factorial4_1_should_be_1() {
        assertEquals(1, factorial(1))
    }

    @Test fun factorial4_2_should_be_2() {
        assertEquals(2, factorial(2))
    }

    @Test fun factorial4_3_should_be_6() {
        assertEquals(6, factorial(3))
    }

    @Test fun factorial4_10_should_be_3628800() {
        assertEquals(3628800, factorial(10))
    }

}

