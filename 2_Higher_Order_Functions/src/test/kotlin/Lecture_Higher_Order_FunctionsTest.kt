
import org.junit.Test
import org.junit.Assert.*

class Lecture_Higher_Order_FunctionsTest {

// Higher Order Functions
//------------------------


    @Test fun sumInts_1_2_3_should_be_6() {
        assertEquals(6, sumInts(1,3))
    }

    @Test fun sumCubes_1_2_3_should_be_36() {
        assertEquals(36, sumCubes(1,3))
    }

    @Test fun sumFactorials_1_2_3_should_be_9() {
        assertEquals(9, sumFactorials(1,3))
    }
}
