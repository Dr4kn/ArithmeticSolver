package arithmeticOperatorSolver

import jdk.jfr.Description
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.math.MathContext

class ArithmeticOperatorsTest {

    @Test
    @Description("No simple rounding like done by an Int division should occur")
    fun calculationAccuracy() {
        assertEquals((3.5).toBigDecimal(MathContext.UNLIMITED),
            calculationFromOperator(ArithmeticOperators.DIVIDE)
                .invoke(
                    (14).toBigDecimal(MathContext.UNLIMITED),
                    (4).toBigDecimal(MathContext.UNLIMITED))
        )
    }

    @Test
    @Description("No simple routing like done by an Int division should occur")
    fun calculationAccuracyForNegativeNumber() {
        assertEquals((-3.5).toBigDecimal(MathContext.UNLIMITED),
            calculationFromOperator(ArithmeticOperators.DIVIDE)
                .invoke(
                    (14).toBigDecimal(MathContext.UNLIMITED),
                    (-4).toBigDecimal(MathContext.UNLIMITED))
        )
    }

    @Test
    fun behaviourForInfiniteQuotient() {
        assertTrue((2).toBigDecimal(MathContext.UNLIMITED)
            .compareTo(
                calculationFromOperator(ArithmeticOperators.DIVIDE)
                    .invoke(
                        (0.2).toBigDecimal(MathContext.UNLIMITED),
                        (0.1).toBigDecimal(MathContext.UNLIMITED))
            ) == 0
        )
    }

    @Test
    fun addingPrecision() {
        assertEquals((0.3).toBigDecimal(MathContext.UNLIMITED),
            calculationFromOperator(ArithmeticOperators.ADD)
                .invoke(
                    (0.2).toBigDecimal(MathContext.UNLIMITED),
                    (0.1).toBigDecimal(MathContext.UNLIMITED))
        )
    }


    @Test
    fun subtractingPrecision() {
        assertEquals((0.1).toBigDecimal(MathContext.UNLIMITED),
            calculationFromOperator(ArithmeticOperators.SUBTRACT)
                .invoke(
                    (0.2).toBigDecimal(MathContext.UNLIMITED),
                    (0.1).toBigDecimal(MathContext.UNLIMITED))
        )
    }


    @Test
    fun multiplyPrecision() {
        assertEquals((7).toBigDecimal(MathContext.UNLIMITED),
            calculationFromOperator(ArithmeticOperators.SUBTRACT)
                .invoke(
                    (3.5).toBigDecimal(MathContext.UNLIMITED),
                    (2).toBigDecimal(MathContext.UNLIMITED))
        )
    }
}