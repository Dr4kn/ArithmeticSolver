package arithmeticOperatorSolver

import java.math.BigDecimal
import java.math.MathContext

fun Byte.toBigDecimal(): BigDecimal = BigDecimal.valueOf(this.toLong())
fun Byte.toBigDecimal(mathContext: MathContext): BigDecimal = BigDecimal(this.toLong(), mathContext)

fun Short.toBigDecimal(): BigDecimal = BigDecimal.valueOf(this.toLong())
fun Short.toBigDecimal(mathContext: MathContext): BigDecimal = BigDecimal(this.toLong(), mathContext)
