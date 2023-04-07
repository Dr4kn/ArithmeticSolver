package arithmeticOperatorSolver

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.MathContext

class ToBigDecimalTest {
    private val mathContext = MathContext.UNLIMITED

    @Nested
    inner class WithoutMathContext {
        @Test
        fun `byte to big decimal`() {
            val b = (127).toByte().toBigDecimal()
            assertEquals(BigDecimal(127), b)
        }

        @Test
        fun `short to big decimal`() {
            val s = (32767).toShort().toBigDecimal()
            assertEquals(BigDecimal(32767), s)
        }
    }

    @Nested
    inner class WithMathContext {
        @Test
        fun `byte to big decimal with mathContext`() {
            val b = (127).toByte().toBigDecimal(mathContext)
            assertEquals(BigDecimal(127, mathContext), b)
        }

        @Test
        fun `short to big decimal with mathContext`() {
            val s = (32767).toShort().toBigDecimal(mathContext)
            assertEquals(BigDecimal(32767, mathContext), s)
        }

        @Test
        fun `number to big decimal with mathContext`() {
            val n: Number = 10
            assertEquals(BigDecimal(10, mathContext), n.toBigDecimal(MathContext.UNLIMITED))
        }
    }

    @Test
    fun `decimal works`() {
        val n: Number = 3.43
        assertEquals(BigDecimal("3.43", mathContext), n.toBigDecimal(MathContext.UNLIMITED))
    }

}