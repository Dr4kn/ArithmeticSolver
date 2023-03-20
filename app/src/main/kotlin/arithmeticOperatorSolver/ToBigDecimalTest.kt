package arithmeticOperatorSolver
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal
import java.math.MathContext

class ToBigDecimalTest {

    @Test
    fun byteToBigDecimal() {
        val b = (127).toByte().toBigDecimal()
        assertEquals(b, BigDecimal(127))
    }

    @Test
    fun byteToBigDecimalMC() {
        val b = (127).toByte().toBigDecimal(MathContext.UNLIMITED)
        assertEquals(b, BigDecimal(127, MathContext.UNLIMITED))
    }

    @Test
    fun shortToBigDecimal() {
        val s = (32767).toShort().toBigDecimal()
        assertEquals(s, BigDecimal(32767))
    }

    @Test
    fun shortToBigDecimalMC() {
        val s = (32767).toShort().toBigDecimal(MathContext.UNLIMITED)
        assertEquals(s, BigDecimal(32767, MathContext.UNLIMITED))
    }

}