
import org.junit.Assert.assertEquals
import org.junit.Test

class Functions_and_EvaluationTest_3_newtons_method {

    // Newton's method to calculate square root
    //------------------------------------------

    @Test fun sqrt_of_0_whould_be_0() {
        assertEquals(0.0, sqrt(0.0), DELTA)
    }

    @Test fun sqrt_of_1_whould_be_1() {
        assertEquals(1.0, sqrt(1.0), DELTA)
    }

    @Test fun sqrt_of_4_whould_be_2() {
        assertEquals(2.0, sqrt(4.0), DELTA)
    }

    @Test fun sqrt_of_0_5() {
        assertEquals(0.707106, sqrt(0.5), DELTA)
    }

    @Test fun sqrt_of_1e20() {
        assertEquals(1e10, sqrt(1e20), DELTA * 1e20)
    }

    val DELTA = 0.001
}
