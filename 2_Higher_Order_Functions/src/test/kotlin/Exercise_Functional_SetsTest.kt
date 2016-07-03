import org.junit.Assert.*
import org.junit.Test

class Exercise_Function_SetsTest {

    // Basic Functions on Sets
    //------------------------

    @Test fun emptySet_should_contain_no_element() {
        assertFalse(contains(emptySet, 1))
    }

// 2.1 Basic Functions on Sets

    // singletonSet

    @Test fun set_should_contain_an_element() {
        assertTrue(contains(singletonSet(1), 1))
    }

    @Test fun set_with_wrong_element() {
        assertFalse(contains(singletonSet(2), 1))
    }

    // union
    @Test fun union_with_emptySet_on_left_side_should_not_change_set() {
        val unionSet = union(emptySet, singletonSet(1))
        assertTrue(contains(unionSet, 1))
        assertFalse(contains(unionSet, 2))
    }

    @Test fun union_with_emptySet_on_right_side_should_not_change_set() {
        val unionSet = union(singletonSet(1), emptySet)
        assertTrue(contains(unionSet, 1))
        assertFalse(contains(unionSet, 2))
    }

    @Test fun union_should_contain_all_elements_of_both_sets() {
        val set1 = singletonSet(1)
        val set2 = singletonSet(2)
        val unionSet = union(set1, set2)
        assertTrue(contains(unionSet, 1))
        assertTrue(contains(unionSet, 2))
        assertFalse(contains(unionSet, 3))
    }

    // intersect
    @Test fun intersect_with_emptySet_on_left_side_should_be_empty_set() {
        val intersectSet = intersect(emptySet, singletonSet(1))
        assertFalse(contains(intersectSet, 1))
    }

    @Test fun intersect_with_emptySet_on_right_side_should_be_empty_set() {
        val intersectSet = intersect(singletonSet(1), emptySet)
        assertFalse(contains(intersectSet, 1))
    }

    @Test fun intersect_should_contain_only_elements_in_both_sets() {
        val set1 = union(singletonSet(1), singletonSet(2))
        val set2 = union(singletonSet(2), singletonSet(3))
        val intersectSet = intersect(set1, set2)
        assertFalse(contains(intersectSet, 1))
        assertTrue(contains(intersectSet, 2))
        assertFalse(contains(intersectSet, 3))
        assertFalse(contains(intersectSet, 4))
    }

    // diff
    @Test fun diff_should_contain_only_elements_in_first_set_which_are_not_in_second() {
        val set1 = union(singletonSet(1), singletonSet(2))
        val set2 = union(singletonSet(2), singletonSet(3))
        val intersectSet = diff(set1, set2)
        assertTrue(contains(intersectSet, 1))
        assertFalse(contains(intersectSet, 2))
        assertFalse(contains(intersectSet, 3))
        assertFalse(contains(intersectSet, 4))
    }

    //filter
    @Test fun filter_should_contain_only_elements_fullfilling_condition() {
        val set = union(singletonSet(1), singletonSet(2))
        val filteredSet = filter(set, {it == 2})
        assertTrue(contains(set, 2))
        assertfalse(contains(set, 1))
    }

    // 2.2 Queries and Transformations on Sets

    // forall
    @Test fun forall_for_empty_set_should_return_true() {
        assertTrue(forall(emptySet, {false}))
    }

    @Test fun forall_even_for_set_of_even_numbers_should_return_true() {
        val set = union(union(singletonSet(2), singletonSet(4)), singletonSet(6))
        assertTrue(forall(set, {it % 2 == 0}))
    }

    @Test fun forall_even_for_set_of_with_mixed_numbers_should_return_false() {
        val set = union(union(singletonSet(2), singletonSet(3)), singletonSet(6))
        assertFalse(forall(set, {it % 2 == 0}))
    }

    // exists
    @Test fun exists_for_empty_set_should_return_false() {
        assertFalse(exists(emptySet, {true}))
    }

    @Test fun exist_odd_for_set_of_even_numbers_should_return_false() {
        val set = union(union(singletonSet(2), singletonSet(4)), singletonSet(6))
        assertFalse(exists(set, {it % 2 != 0}))
    }

    @Test fun exist_odd_for_set_of_with_mixed_numbers_should_return_true() {
        val set = union(union(singletonSet(2), singletonSet(3)), singletonSet(6))
        assertTrue(exists(set, {it % 2 != 0}))
    }

    // map
    @Test fun map_for_empty_set_should_return_empty_set() {
        val mappedSet = map(emptySet, {it*it})
        assertFalse(contains(mappedSet, 1))
    }

    @Test fun map_square_should_return_set_of_squares() {
        val set = union(union(singletonSet(1), singletonSet(2)), singletonSet(3))
        val mappedSet = map(set, {it*it})
        assertTrue(contains(mappedSet, 1))
        assertFalse(contains(mappedSet, 2))
        assertTrue(contains(mappedSet, 4))
        assertFalse(contains(mappedSet, 3))
        assertTrue(contains(mappedSet, 9))
    }

}