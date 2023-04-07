package arithmeticOperatorSolver

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
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


    @Nested
    inner class DivisionRoundingTesting {
        @Test
        fun `does not round with positive numbers`() {
            val expected = (3.5).toBigDecimal(mathContext)
            val actual = calculate(14, 4, ArithmeticOperators.DIVIDE)

            assertThat(expected, Matchers.comparesEqualTo(actual))
        }

        @Test
        fun `does not round with negative numbers`() {
            val expected = (-3.5).toBigDecimal(mathContext)
            val actual = calculate(14, -4, ArithmeticOperators.DIVIDE)
            assertThat(expected, Matchers.comparesEqualTo(actual))
        }

        @Test
        fun `behaviour for infinite quotient`() {
            val expected = (2).toBigDecimal(mathContext)
            val actual = calculate(0.2, 0.1, ArithmeticOperators.DIVIDE)
            assertThat(expected, Matchers.comparesEqualTo(actual))
        }
    }


    @Nested
    inner class OperatorPrecisionTesting {
        @Test
        fun `adding precision`() {
            val expected = (0.3).toBigDecimal(mathContext)
            val actual = calculate(0.2, 0.1, ArithmeticOperators.ADD)
            assertThat(expected, Matchers.comparesEqualTo(actual))
        }

        @Test
        fun `subtracting precision`() {
            val expected = (0.1).toBigDecimal(mathContext)
            val actual = calculate(0.2, 0.1, ArithmeticOperators.SUBTRACT)
            assertThat(expected, Matchers.comparesEqualTo(actual))
        }

        @Test
        fun `multiply precision`() {
            val expected = (7).toBigDecimal(mathContext)
            val actual = calculate(3.5, 2, ArithmeticOperators.MULTIPLY)
            assertThat(expected, Matchers.comparesEqualTo(actual))
        }
    }


    @Nested
    inner class DecimalPrecisionTesting {
        @Test
        fun `sum has more decimal places`() {
            val expected = (5.500000).toBigDecimal(mathContext)
            val actual = calculate(3.500, 2, ArithmeticOperators.ADD)
            assertThat(expected, Matchers.comparesEqualTo(actual))
        }

        @Test
        fun `difference has more decimal places`() {
            val expected = (0.401300).toBigDecimal(mathContext)
            val actual = calculate(3, 2.5987, ArithmeticOperators.SUBTRACT)
            assertThat(expected, Matchers.comparesEqualTo(actual))
        }

        @Test
        fun `product has more decimal places`() {
            val expected = (9.8336).toBigDecimal(mathContext)
            val actual = calculate(3.512, 2.8, ArithmeticOperators.MULTIPLY)
            assertThat(expected, Matchers.comparesEqualTo(actual))
        }

        @Test
        fun `quotient has more decimal places`() {
            val expected = (1.577047311).toBigDecimal(mathContext)
            val actual = calculate(8.3, 5.263, ArithmeticOperators.DIVIDE)
            assertThat(expected, Matchers.comparesEqualTo(actual))
        }

        @Test
        fun `max decimal places that were decided`() {
            // there isn't a right or wrong here just if it matches the max that is specified.
            // can be changed to a lower number for performance or a higher one if the increased precision is needed
            val expected = (0.333_333_333_3).toBigDecimal(mathContext)
            val actual = calculate(1, 3, ArithmeticOperators.DIVIDE)
            assertThat(expected, Matchers.comparesEqualTo(actual))
        }
    }
}