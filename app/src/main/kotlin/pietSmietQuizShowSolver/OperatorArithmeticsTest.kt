package pietSmietQuizShowSolver

import jdk.jfr.Description
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class OperatorArithmeticsTest {

    @Test
    @Description("No simple rounding like done by an Int division should occur")
    fun calculationAccuracy() {
        assertEquals(3.5, OperatorArithmetics.DIVIDE.apply(14, 4))
    }

    @Test
    @Description("No simple routing like done by an Int division should occur")
    fun calculationAccuracyForNegativeNumber() {
        assertEquals(-3.5, OperatorArithmetics.DIVIDE.apply(14, -4))
    }

    @Test
    fun behaviourForInfiniteQuotient() {
        assertEquals(2, OperatorArithmetics.DIVIDE.apply(0.2, 0.1))
    }

    @Test
    fun addingPrecision() {
        assertEquals(0.3, OperatorArithmetics.ADD.apply(0.2, 0.1))
    }


    @Test
    fun subtractingPrecision() {
        assertEquals(0.1, OperatorArithmetics.SUBTRACT.apply(0.2, 0.1))
    }


    @Test
    fun multiplyPrecision() {
        assertEquals(7, OperatorArithmetics.SUBTRACT.apply(3.5, 2))
    }
}