
import org.junit.Test
import org.junit.Assert.*
import org.junit.Ignore

// Tail recursion

class Functions_and_EvaluationTest_4_gcd {

    // Eucid's algorithm gcd greatest common divisor
    //-----------------------------------------------

    @Test fun gcd_1_1_should_be_1() {
        assertEquals(1, gcd(1, 1))
    }

    @Test fun gcd_2_2_should_be_2() {
        assertEquals(2, gcd(2, 2))
    }

    @Test fun gcd_2_3_should_be_1() {
        assertEquals(1, gcd(2, 3))
    }

    @Test fun gcd_2_6_should_be_2() {
        assertEquals(2, gcd(2, 6))
    }

    @Test fun gcd_6_2_should_be_2() {
        assertEquals(2, gcd(6, 2))
    }

    @Test fun gcd_6_10_should_be_2() {
        assertEquals(2, gcd(6, 10))
    }

    @Test fun gcd_3528_3780_should_be_252() {
        assertEquals(252, gcd(3528, 3780))
    }

    @Test fun gcd_3780_3528_should_be_252() {
        assertEquals(252, gcd(3780, 3528))
    }

    @Test(expected = IllegalArgumentException::class) fun gcd_0_1_not_defined() {
        gcd(0, 1)
    }
}

