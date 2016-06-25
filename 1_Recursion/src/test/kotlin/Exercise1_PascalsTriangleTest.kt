import org.junit.Test
import org.junit.Assert.*

class PascalsTriangleTest {
    @Test fun first_row_should_be_1() {
        assertEquals(1, pascal(0,0))
    }

    @Test fun second_row_should_be_1_1() {
        assertEquals(1, pascal(0,1))
        assertEquals(1, pascal(1,1))
    }

    @Test fun third_row_should_be_1_2_1() {
        assertEquals(1, pascal(2,2))
        assertEquals(2, pascal(1,2))
        assertEquals(1, pascal(2,2))
    }

    @Test fun print_triangle() {
        for(r in 0..4) {
            for (c in 0..4) {
                val p = pascal(c, r)
                print(if (p == 0) " " else "$p")
            }
            println()
        }
    }
}
