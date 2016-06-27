
import org.junit.Test
import org.junit.Assert.*
import org.junit.Ignore

class Functions_and_EvaluationTest {

    // Lazy parameter evaluation through closures

    @Test @Ignore fun loop_never_terminates() {  // Don't call loop, it never terminates
        loop()
    }

    @Test @Ignore fun parameter_loop_by_value_never_terminates() {  // Don't call, it never terminates
        ignoreParameter(loop())
    }
    @Test fun parameter_loop_by_name() {  // Can be called, closure is never evaluated
        ignoreParameterWithClosure( {loop()} )
    }

    // Conditional boolean expressions using closure

    @Test fun true_and_false_should_be_false() {
        assertFalse(conditionalAnd(true, {false}))
    }

    @Test fun second_parameter_in_conditionalAnd_should_not_be_evaluated_if_first_parameter_is_false() {
        assertFalse(conditionalAnd(false, {loop()}))
    }

    @Test fun true_and_true_should_be_true() {
        assertTrue(conditionalAnd(true, {true}))
    }

    @Test fun true_or_false_should_be_true() {
        assertTrue(conditionalOr(true, {false}))
    }

    @Test fun second_parameter_in_conditionalOr_should_not_be_evaluated_if_first_parameter_is_true() {
        assertTrue(conditionalOr(true, {loop()}))
    }

    @Test fun false_or_false_should_be_false() {
        assertFalse(conditionalOr(false, {false}))
    }

    // Newton's method to calculate square root

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
        assertEquals(1e10, sqrt(1e20), DELTA*1e20)
    }

    val DELTA = 0.001
}
