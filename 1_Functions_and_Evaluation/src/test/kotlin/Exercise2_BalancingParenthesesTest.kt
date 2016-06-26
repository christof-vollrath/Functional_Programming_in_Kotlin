import org.junit.Test
import org.junit.Assert.*

class BalancingParenthesesTest {
    @Test fun empty_string_should_be_balanced() {
        assertTrue(balance(""))
    }

    @Test fun string_without_parentheses_should_be_balanced() {
        assertTrue(balance("String example"))
    }

    @Test fun string_with_only_opening_parentheses_shouldnt_be_balanced() {
        assertFalse(balance("("))
    }

    @Test fun string_with_only_closing_parentheses_shouldnt_be_balanced() {
        assertFalse(balance(")"))
    }

    @Test fun string_with_only_opening_parentheses_and_other_chars_shouldnt_be_balanced() {
        assertFalse(balance("a(b"))
    }

    @Test fun string_with_opening_and_closing_parentheses_should_be_balanced() {
        assertTrue(balance("()"))
    }

    @Test fun string_with_opening_and_closing_parentheses_mixed_with_other_chars_should_be_balanced() {
        assertTrue(balance("a(b)c"))
    }

    @Test fun nested_balanced_parentheses() {
        assertTrue(balance("a(b(c)d)e"))
    }

    @Test fun nested_balanced_parentheses_which_are_not_balanced() {
        assertFalse(balance("a(b(c)d"))
    }

    @Test fun example1() {
        assertTrue(balance("(if (zero? x) max (/ 1 x))"))
    }

    @Test fun example2() {
        assertTrue(balance("I told him (that it’s not (yet) done). (But he wasn’t listening)"))
    }

    @Test fun example3() {
        assertFalse(balance(":-)"))
    }

    @Test fun example4() {
        assertFalse(balance("())("))
    }
}
