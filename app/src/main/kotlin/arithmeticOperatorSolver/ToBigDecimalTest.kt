package arithmeticOperatorSolver
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal
import java.math.MathContext

class ToBigDecimalTest {
    private val mathContext = MathContext.UNLIMITED

    @Test
    fun byteToBigDecimal() {
        val b = (127).toByte().toBigDecimal()
        assertEquals(BigDecimal(127), b)
    }

    @Test
    fun byteToBigDecimalMC() {
        val b = (127).toByte().toBigDecimal(mathContext)
        assertEquals(BigDecimal(127, mathContext), b)
    }

    @Test
    fun shortToBigDecimal() {
        val s = (32767).toShort().toBigDecimal()
        assertEquals(BigDecimal(32767), s)
    }

    @Test
    fun shortToBigDecimalMC() {
        val s = (32767).toShort().toBigDecimal(mathContext)
        assertEquals(BigDecimal(32767, mathContext), s)
    }

    @Test
    fun numberToBigDecimalMC() {
        val n:Number = 10
        assertEquals(BigDecimal(10, mathContext), n.toBigDecimal(MathContext.UNLIMITED))
    }

    @Test
    fun decimalWorks() {
        val n:Number = 3.43
        assertEquals(BigDecimal("3.43", mathContext), n.toBigDecimal(MathContext.UNLIMITED))
    }

}