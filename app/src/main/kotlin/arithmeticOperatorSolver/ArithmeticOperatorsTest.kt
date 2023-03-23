package arithmeticOperatorSolver

import jdk.jfr.Description
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.math.MathContext

class ArithmeticOperatorsTest {
    private val mathContext = MathContext.UNLIMITED

    @Test
    @Description("No simple rounding like done by an Int division should occur")
    fun calculationAccuracy() {
        assertEquals((3.5).toBigDecimal(mathContext),
            calculationFromOperator(ArithmeticOperators.DIVIDE)
                .invoke(
                    (14).toBigDecimal(mathContext),
                    (4).toBigDecimal(mathContext))
        )
    }

    @Test
    @Description("No simple routing like done by an Int division should occur")
    fun calculationAccuracyForNegativeNumber() {
        assertEquals((-3.5).toBigDecimal(mathContext),
            calculationFromOperator(ArithmeticOperators.DIVIDE)
                .invoke(
                    (14).toBigDecimal(mathContext),
                    (-4).toBigDecimal(mathContext))
        )
    }

    @Test
    fun behaviourForInfiniteQuotient() {
        assertTrue((2).toBigDecimal(mathContext)
            .compareTo(
                calculationFromOperator(ArithmeticOperators.DIVIDE)
                    .invoke(
                        (0.2).toBigDecimal(mathContext),
                        (0.1).toBigDecimal(mathContext))
            ) == 0
        )
    }

    @Test
    fun addingPrecision() {
        assertEquals((0.3).toBigDecimal(mathContext),
            calculationFromOperator(ArithmeticOperators.ADD)
                .invoke(
                    (0.2).toBigDecimal(mathContext),
                    (0.1).toBigDecimal(mathContext))
        )
    }

    @Test
    fun subtractingPrecision() {
        assertEquals((0.1).toBigDecimal(mathContext),
            calculationFromOperator(ArithmeticOperators.SUBTRACT)
                .invoke(
                    (0.2).toBigDecimal(mathContext),
                    (0.1).toBigDecimal(mathContext))
        )
    }

    @Test
    fun multiplyPrecision() {
        assertTrue((7).toBigDecimal(mathContext)
            .compareTo(
                calculationFromOperator(ArithmeticOperators.MULTIPLY)
                    .invoke(
                        (3.5).toBigDecimal(mathContext),
                        (2).toBigDecimal(mathContext))
            ) == 0
        )
    }
}