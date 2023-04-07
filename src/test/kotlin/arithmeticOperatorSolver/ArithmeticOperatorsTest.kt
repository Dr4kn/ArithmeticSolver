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
        assertEquals(
            (3.5).toBigDecimal(mathContext),
            calculationFromOperator(ArithmeticOperators.DIVIDE)
                .invoke(
                    (14).toBigDecimal(mathContext),
                    (4).toBigDecimal(mathContext)
                )
        )
    }

    @Test
    @Description("No simple routing like done by an Int division should occur")
    fun calculationAccuracyForNegativeNumber() {
        assertEquals(
            (-3.5).toBigDecimal(mathContext),
            calculationFromOperator(ArithmeticOperators.DIVIDE)
                .invoke(
                    (14).toBigDecimal(mathContext),
                    (-4).toBigDecimal(mathContext)
                )
        )
    }

    @Test
    fun behaviourForInfiniteQuotient() {
        assertTrue(
            (2).toBigDecimal(mathContext)
                .compareTo(
                    calculationFromOperator(ArithmeticOperators.DIVIDE)
                        .invoke(
                            (0.2).toBigDecimal(mathContext),
                            (0.1).toBigDecimal(mathContext)
                        )
                ) == 0
        )
    }

    @Test
    fun addingPrecision() {
        assertEquals(
            (0.3).toBigDecimal(mathContext),
            calculationFromOperator(ArithmeticOperators.ADD)
                .invoke(
                    (0.2).toBigDecimal(mathContext),
                    (0.1).toBigDecimal(mathContext)
                )
        )
    }

    @Test
    fun subtractingPrecision() {
        assertEquals(
            (0.1).toBigDecimal(mathContext),
            calculationFromOperator(ArithmeticOperators.SUBTRACT)
                .invoke(
                    (0.2).toBigDecimal(mathContext),
                    (0.1).toBigDecimal(mathContext)
                )
        )
    }

    @Test
    fun multiplyPrecision() {
        assertTrue(
            (7).toBigDecimal(mathContext)
                .compareTo(
                    calculationFromOperator(ArithmeticOperators.MULTIPLY)
                        .invoke(
                            (3.5).toBigDecimal(mathContext),
                            (2).toBigDecimal(mathContext)
                        )
                ) == 0
        )
    }

    @Test
    fun sumHasMoreDecimalPlaces() {
        val sum = calculationFromOperator(ArithmeticOperators.ADD)
            .invoke(
                (3.500).toBigDecimal(mathContext),
                (2).toBigDecimal(mathContext)
            )

        assertTrue(
            (5.500000).toBigDecimal(mathContext)
                .compareTo(sum) == 0
        )
    }

    @Test
    fun differenceHasMoreDecimalPlaces() {
        val difference = calculationFromOperator(ArithmeticOperators.SUBTRACT)
            .invoke(
                (3).toBigDecimal(mathContext),
                (2.5987).toBigDecimal(mathContext)
            )

        assertTrue(
            (0.401300).toBigDecimal(mathContext)
                .compareTo(difference) == 0
        )
    }

    @Test
    fun productHasMoreDecimalPlaces() {
        val product = calculationFromOperator(ArithmeticOperators.MULTIPLY)
            .invoke(
                (3.512).toBigDecimal(mathContext),
                (2.8).toBigDecimal(mathContext)
            )

        assertTrue(
            (9.8336).toBigDecimal(mathContext)
                .compareTo(product) == 0
        )
    }

    @Test
    fun quotientHasMoreDecimalPlaces() {
        val quotient = calculationFromOperator(ArithmeticOperators.DIVIDE)
            .invoke(
                (8.3).toBigDecimal(mathContext),
                (5.263).toBigDecimal(mathContext)
            )

        assertTrue(
            (1.577047311).toBigDecimal(mathContext)
                .compareTo(quotient) == 0
        )
    }

    @Test
    fun maxDecimalPlaces() {
        // there isn't a right or wrong here just if it matches the max that is specified.
        // can be changed to a lower number for performance or a higher one if the increased precision is needed
        val fraction = calculationFromOperator(ArithmeticOperators.DIVIDE)
            .invoke(
                (1).toBigDecimal(mathContext),
                (3).toBigDecimal(mathContext)
            )
        assertTrue(
            (0.333_333_333_3).toBigDecimal(mathContext)
                .compareTo(fraction) == 0
        )
    }
}