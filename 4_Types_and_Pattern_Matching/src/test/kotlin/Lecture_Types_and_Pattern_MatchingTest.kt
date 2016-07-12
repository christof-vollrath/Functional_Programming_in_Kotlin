import org.junit.Test
import org.junit.Assert.*


class TypesAndPatternMatchingTest {

    // Pure Booleans

    // ifThenElse

    @Test fun ifThenElse_false_should_return_else() {
       assertEquals(2, pureFalse.ifThenElse(1,2))
    }
    @Test fun ifThenElse_true_should_return_true() {
        assertEquals(1, pureTrue.ifThenElse(1,2))
    }

    // and
    @Test fun and_true_true_should_return_true() {
        assertEquals(pureTrue, pureTrue.and(pureTrue))
    }
    @Test fun and_false_false_should_return_false() {
        assertEquals(pureFalse, pureFalse.and(pureFalse))
    }
    @Test fun and_true_false_should_return_false() {
        assertEquals(pureFalse, pureTrue.and(pureFalse))
    }
    @Test fun and_false__true_should_return_false() {
        assertEquals(pureFalse, pureFalse.and(pureTrue))
    }

    // or
    @Test fun or_true_true_should_return_true() {
        assertEquals(pureTrue, pureTrue.or(pureTrue))
    }
    @Test fun or_false_false_should_return_false() {
        assertEquals(pureFalse, pureFalse.or(pureFalse))
    }
    @Test fun or_true_false_should_return_true() {
        assertEquals(pureTrue, pureTrue.or(pureFalse))
    }
    @Test fun or_false__true_should_return_true() {
        assertEquals(pureTrue, pureFalse.or(pureTrue))
    }

    // not
    @Test fun not_true_should_return_false() {
        assertEquals(pureFalse, pureTrue.not())
    }
    @Test fun not_false_should_return_true() {
        assertEquals(pureTrue, pureFalse.not())
    }

    // equals
    @Test fun equals_true_true_should_return_true() {
        assertEquals(pureTrue, pureTrue.equals(pureTrue))
    }
    @Test fun equals_false_false_should_return_true() {
        assertEquals(pureTrue, pureFalse.equals(pureFalse))
    }
    @Test fun equals_false_true_should_return_false() {
        assertEquals(pureFalse, pureFalse.equals(pureTrue))
    }
    @Test fun equals_true_false_should_return_false() {
        assertEquals(pureFalse, pureTrue.equals(pureFalse))
    }
    // unequals
    @Test fun unequals_true_true_should_return_false() {
        assertEquals(pureFalse, pureTrue.unequals(pureTrue))
    }
    @Test fun unequals_false_false_should_return_false() {
        assertEquals(pureFalse, pureFalse.unequals(pureFalse))
    }
    @Test fun unequals_false_true_should_return_true() {
        assertEquals(pureTrue, pureTrue.unequals(pureFalse))
    }
    @Test fun unequals_true_false_should_return_true() {
        assertEquals(pureTrue, pureFalse.unequals(pureTrue))
    }
}

