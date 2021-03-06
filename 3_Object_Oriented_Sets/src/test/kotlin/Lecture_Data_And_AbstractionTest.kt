import org.junit.Test
import org.junit.Assert.*

// 3.1 Class Hierarchies

// IntSet

class IntSetTest {
    @Test fun empty_should_contain_nothing() {
       assertFalse(Empty.contains(1))
    }

    @Test fun set_with_one_element_should_contain_exactly_this_element() {
        val set1 = Empty.incl(1)
        assertTrue(set1.contains(1))
        assertFalse(set1.contains(2))

    }

    @Test fun set_with_three_elements_should_contain_exactly_these_elements() {
        val set_1_5_3 = Empty.incl(1).incl(5).incl(3)
        assertTrue(set_1_5_3.contains(1))
        assertTrue(set_1_5_3.contains(3))
        assertTrue(set_1_5_3.contains(5))
        assertFalse(set_1_5_3.contains(2))
    }

    @Test fun print_intset() {
        val set = Empty.incl(9).incl(4).incl(1).incl(2).incl(5).incl(3).incl(8).incl(7).incl(6)
        assertEquals("{{{.1{.2{.3.}}}4{.5{{{.6.}7.}8.}}}9.}", set.toString())
    }

    @Test fun union_with_empty_set_should_return_set() {
        val set1 = Empty.incl(1)
        val unionSet = Empty.union(set1)
        assertTrue(unionSet.contains(1))
    }
    @Test fun union_should_return_set_with_all_elements() {
        val set1_3 = Empty.incl(1).incl(3)
        val set2_4 = Empty.incl(2).incl(4)
        val unionSet = set1_3.union(set2_4)
        assertTrue(unionSet.contains(1))
        assertTrue(unionSet.contains(2))
        assertTrue(unionSet.contains(3))
        assertTrue(unionSet.contains(4))
    }

    // 3.3 Parameterized Types

    // ConsList

    @Test fun empty_ConsList_should_be_empty() {
        assertTrue(Nil<Int>().empty)
    }

    @Test fun a_ConsList_with_one_element_should_contain_it_as_head() {
        assertEquals(1, Cons(1, Nil()).head)
        assertTrue(Cons(1, Nil()).tail.empty)
    }

    @Test fun singleton_should_create_a_list_with_one_element() {
        assertEquals(1, singleton(1).head)
        assertTrue(singleton(1).tail.empty)
    }

    @Test(expected=IndexOutOfBoundsException::class) fun nth_0_for_empty_list_should_throw_exception() {
        nth(0, Nil<Int>())
    }
    @Test fun nth_0_for_list_with_one_element_should_return_element() {
        assertEquals(1, nth(0, singleton(1)))
    }
    @Test fun nth_2_should_return_element() {
        assertEquals(3, nth(2, Cons(1, Cons(2, singleton(3)))))
    }
    @Test(expected=IndexOutOfBoundsException::class) fun nth_3_with_too_small_list_should_throw_exception() {
        nth(3, Cons(1, Cons(2, singleton(3))))
    }

    // Type erasure

    // While Java and Scala have type erasure,
    // in Kotlin the combination of inline and reified allows access to runtime types

    @Test fun typeOfParam_for_String() {
        assertEquals("kotlin.String", typeOfParam("a simple string"))
    }
    @Test fun typeOfParam_for_Int() {
        assertEquals("kotlin.Int", typeOfParam(1))
    }
    @Test fun typeOfParam_for_ConsList() {
        assertEquals("Cons", typeOfParam(singleton(1)))
    }
}
