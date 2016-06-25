import org.junit.Test
import com.google.common.truth.Truth.*

class CountingChangeTest {
    @Test fun countChange_for_0_is_empty() {
        assertThat(countChange(0, listOf(2, 1))).isEqualTo(0)
    }

    @Test fun change_for_0_is_empty_list() {
        assertThat(change(0, listOf(2, 1))).containsExactly(
                listOf<Int>()
        )    }

    @Test fun countChange_for_no_coins_is_empty() {
        assertThat(countChange(1, listOf())).isEqualTo(0)
    }

    @Test fun change_for_no_coins_is_empty() {
        assertThat(change(1, listOf())).isEmpty()
    }

    @Test fun countChange_for_one_coin_which_fits() {
        assertThat(countChange(1, listOf(1))).isEqualTo(1)
    }

    @Test fun change_for_one_coin_which_fits() {
        assertThat(change(1, listOf(2, 1))).containsExactly(
            listOf(1)
        )
    }

    @Test fun change_which_needs_all_coins() {
        assertThat(change(3, listOf(2, 1))).containsExactly(
                listOf(2, 1), listOf(1, 1, 1)
        )
    }

    @Test fun change_which_doesnt_need_all_coins() {
        assertThat(change(3, listOf(5, 2, 1))).containsExactly(
                listOf(2, 1), listOf(1, 1, 1)
        )
    }

    @Test fun change_which_doesnt_need_all_coins_2() {
        assertThat(change(4, listOf(5, 2, 1))).containsExactly(
                listOf(2, 2), listOf(2, 1, 1), listOf(1, 1, 1, 1)
        )
    }

    @Test fun change_which_one_coin_two_times() {
        assertThat(change(2, listOf(5, 1))).containsExactly(
                listOf(1, 1)
        )
    }
}
