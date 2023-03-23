package arithmeticOperatorSolver

import java.math.BigDecimal
import java.math.MathContext

fun Byte.toBigDecimal(): BigDecimal = this.toString().toBigDecimal()
fun Byte.toBigDecimal(mathContext: MathContext): BigDecimal = this.toString().toBigDecimal(mathContext)

fun Short.toBigDecimal(): BigDecimal = this.toString().toBigDecimal()
fun Short.toBigDecimal(mathContext: MathContext): BigDecimal = this.toString().toBigDecimal(mathContext)

fun Number.toBigDecimal(mathContext: MathContext): BigDecimal = this.toString().toBigDecimal(mathContext)