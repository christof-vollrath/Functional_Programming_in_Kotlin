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

    // less
    @Test fun less_false_true_should_return_true() {
        assertEquals(pureTrue, pureFalse.less(pureTrue))
    }
    @Test fun less_false_false_should_return_false() {
        assertEquals(pureFalse, pureFalse.less(pureFalse))
    }
    @Test fun less_true_false_should_return_false() {
        assertEquals(pureFalse, pureTrue.less(pureFalse))
    }
    @Test fun less_true_true_should_return_false() {
        assertEquals(pureFalse, pureTrue.less(pureTrue))
    }

    // Nat (simple variant of pure integers)

    // zero
    @Test fun zero_should_be_zero() {
        assertTrue(zero.isZero())
    }
    // Succ
    @Test fun one_should_not_be_zero() {
        assertFalse(Succ(zero).isZero())
    }
    @Test fun one_should_have_predecessor_zero() {
        assertTrue(Succ(zero).predecessor().isZero())
    }
    // Helper function toInt
    @Test fun zero_toInt_should_be_0() {
        assertEquals(0, zero.toInt())
    }
    @Test fun one_toInt_should_be_1() {
        assertEquals(1, Succ(zero).toInt())
    }
    @Test fun three_toInt_should_be_3() {
        assertEquals(3, Succ(Succ(Succ(zero))).toInt())
    }
    // Helper function toNat
    @Test fun toNat_0_should_be_zero() {
        assertEquals(0, 0.toNat().toInt())
    }
    @Test fun toNat_1_should_be_one() {
        assertEquals(1, 1.toNat().toInt())
    }
    @Test fun toNat_2_should_be_two() {
        assertEquals(2, 2.toNat().toInt())
    }
    // predecessor
    @Test(expected = IllegalArgumentException::class) fun zero_predecessor_should_throw_exception() {
        zero.predecessor()
    }
    @Test fun one_predecessor_should_be_zero() {
        assertEquals(0, 1.toNat().predecessor().toInt())
    }
    @Test fun ten_predecessor_should_be_nine() {
        assertEquals(9, 10.toNat().predecessor().toInt())
    }
    // successor
    @Test fun zero_successor_should_be_one() {
        assertEquals(1, 0.toNat().successor().toInt())
    }
    @Test fun one_successor_should_be_two() {
        assertEquals(2, 1.toNat().successor().toInt())
    }
    // plus
    @Test fun zero_plus_zero_should_be_zero() {
        assertEquals(0, ((0.toNat())+(0.toNat())).toInt())
    }
    @Test fun zero_plus_one_should_be_one() {
        assertEquals(1, ((0.toNat())+(1.toNat())).toInt())
    }
    @Test fun one_plus_one_should_be_two() {
        assertEquals(2, ((1.toNat())+(1.toNat())).toInt())
    }
    @Test fun ten_plus_five_should_be_fifteen() {
        assertEquals(15, ((10.toNat())+(5.toNat())).toInt())
    }
    // minus
    @Test fun zero_minus_zero_should_be_zero() {
        assertEquals(0, ((0.toNat())-(0.toNat())).toInt())
    }
    @Test(expected = IllegalArgumentException::class)  fun zero_minus_one_should_throw_exception() {
        ((0.toNat())-(1.toNat())).toInt()
    }
    @Test fun one_minus_zero_should_be_one() {
        assertEquals(1, ((1.toNat())-(0.toNat())).toInt())
    }
    @Test fun one_minus_one_should_be_zero() {
        assertEquals(0, ((1.toNat())-(1.toNat())).toInt())
    }
    @Test fun ten_minus_three_should_be_seven() {
        assertEquals(7, ((10.toNat())-(3.toNat())).toInt())
    }

    // Expressions of Number and Sum

    @Test fun eval_expression_with_sum() {
        assertEquals(3, Sum(Num(1), Num(2)).eval())
    }
    @Test fun eval_expression_with_sum_and_prod() {
        assertEquals(7, Sum(Num(1), Prod(Num(2), Num(3))).eval())
    }
    @Test fun show_num() {
        assertEquals("1", show(Num(1)))
    }
    @Test fun show_sum() {
        assertEquals("1 + 2", show(Sum(Num(1), Num(2))))
    }
    @Test fun show_prod() {
        assertEquals("1 * 2", show(Prod(Num(1), Num(2))))
    }
    @Test fun show_sum_prod_with_parentheses() {
        assertEquals("(1 + 2) * 3", show(Prod(Sum(Num(1), Num(2)), Num(3))))
    }

    // Lists
    @Test fun head_should_return_first_element() {
        val h = listOf(1,2,3).first()
        assertEquals(1, h)
    }
    @Test fun tail_should_return_list_without_first_element() {
        val t = listOf(1,2,3).tail() // With fun<T> List<T>.tail(): List<T> = subList(1, size)
        assertEquals(listOf(2,3), t)
    }
    @Test fun append_element_to_list() {
        val l = listOf(1,2) + 3
        assertEquals(listOf(1,2,3), l)
    }
    @Test fun append_two_lists() {
        val l = listOf(1) + listOf(2,3) // Also example for prepend
        assertEquals(listOf(1,2,3), l)
    }
}

