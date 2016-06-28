
import org.junit.Test
import org.junit.Assert.*
import org.junit.Ignore

class Functions_and_EvaluationTest_lazy_evaluation {

    // Lazy parameter evaluation through closures
    //--------------------------------------------

    @Test @Ignore fun loop_never_terminates() {  // Don't call loop, it never terminates
        loop()
    }

    @Test @Ignore fun parameter_loop_by_value_never_terminates() {  // Don't call, it never terminates
        ignoreParameter(loop())
    }

    @Test fun parameter_loop_by_name() {  // Can be called, closure is never evaluated
        ignoreParameterWithClosure({ loop() })
    }
}
