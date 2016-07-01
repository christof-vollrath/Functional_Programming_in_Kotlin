import org.junit.Assert.*
import org.junit.Test

class Exercise_Function_SetsTest {

    // Basic Functions on Sets
    //------------------------

    @Test fun empty_set_should_contain_no_element() {
        assertFalse(contains(FunctionalSet(), 1))
    }

    @Test fun set_should_contain_an_element() {
        assertTrue(contains(singletonSet(1), 1))
    }
}