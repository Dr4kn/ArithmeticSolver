package arithmeticOperatorSolver

import jdk.jfr.Description
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal
import java.math.MathContext

class ArithmeticOperatorsTest {
    private val mathContext = MathContext.UNLIMITED

    private fun calculate(number1: Number, number2: Number, operator: ArithmeticOperators): BigDecimal {
        val mathContext = MathContext.UNLIMITED
        return calculate(number1, number2, operator, mathContext)
    }

    private fun calculate(
        number1: Number,
        number2: Number,
        operator: ArithmeticOperators,
        mathContext: MathContext
    ): BigDecimal {
        return calculationFromOperator(operator)
            .invoke(
                (number1).toBigDecimal(mathContext),
                (number2).toBigDecimal(mathContext)
            )
    }

    @Test
    @Description("No simple rounding like done by an Int division should occur")
    fun calculationAccuracy() {
        val expected = (3.5).toBigDecimal(mathContext)
        val actual = calculate(14, 4, ArithmeticOperators.DIVIDE)

        assertThat(expected, Matchers.comparesEqualTo(actual))
    }

    @Test
    @Description("No simple routing like done by an Int division should occur")
    fun calculationAccuracyForNegativeNumber() {
        val expected = (-3.5).toBigDecimal(mathContext)
        val actual = calculate(14, -4, ArithmeticOperators.DIVIDE)
        assertThat(expected, Matchers.comparesEqualTo(actual))
    }

    @Test
    fun behaviourForInfiniteQuotient() {
        val expected = (2).toBigDecimal(mathContext)
        val actual = calculate(0.2, 0.1, ArithmeticOperators.DIVIDE)
        assertThat(expected, Matchers.comparesEqualTo(actual))
    }

    @Test
    fun addingPrecision() {
        val expected = (0.3).toBigDecimal(mathContext)
        val actual = calculate(0.2, 0.1, ArithmeticOperators.ADD)
        assertThat(expected, Matchers.comparesEqualTo(actual))
    }

    @Test
    fun subtractingPrecision() {
        val expected = (0.1).toBigDecimal(mathContext)
        val actual = calculate(0.2, 0.1, ArithmeticOperators.SUBTRACT)
        assertThat(expected, Matchers.comparesEqualTo(actual))
    }


    @Test
    fun multiplyPrecision() {
        val expected = (7).toBigDecimal(mathContext)
        val actual = calculate(3.5, 2, ArithmeticOperators.MULTIPLY)
        assertThat(expected, Matchers.comparesEqualTo(actual))
    }

    @Test
    fun sumHasMoreDecimalPlaces() {
        val expected = (5.500000).toBigDecimal(mathContext)
        val actual = calculate(3.500, 2, ArithmeticOperators.ADD)
        assertThat(expected, Matchers.comparesEqualTo(actual))
    }

    @Test
    fun differenceHasMoreDecimalPlaces() {
        val expected = (0.401300).toBigDecimal(mathContext)
        val actual = calculate(3, 2.5987, ArithmeticOperators.SUBTRACT)
        assertThat(expected, Matchers.comparesEqualTo(actual))
    }

    @Test
    fun productHasMoreDecimalPlaces() {
        val expected = (9.8336).toBigDecimal(mathContext)
        val actual = calculate(3.512, 2.8, ArithmeticOperators.MULTIPLY)
        assertThat(expected, Matchers.comparesEqualTo(actual))
    }

    @Test
    fun quotientHasMoreDecimalPlaces() {
        val expected = (1.577047311).toBigDecimal(mathContext)
        val actual = calculate(8.3, 5.263, ArithmeticOperators.DIVIDE)
        assertThat(expected, Matchers.comparesEqualTo(actual))
    }

    @Test
    fun maxDecimalPlaces() {
        // there isn't a right or wrong here just if it matches the max that is specified.
        // can be changed to a lower number for performance or a higher one if the increased precision is needed
        val expected = (0.333_333_333_3).toBigDecimal(mathContext)
        val actual = calculate(1, 3, ArithmeticOperators.DIVIDE)
        assertThat(expected, Matchers.comparesEqualTo(actual))
    }
}