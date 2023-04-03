package arithmeticOperatorSolver

import java.math.BigDecimal
import java.math.MathContext

enum class ArithmeticOperators {
    // this is also the order the operations have to occur in
    // otherwise the code doesn't work correctly unless you write this order separately or a parser
    MULTIPLY,
    DIVIDE,
    SUBTRACT,
    ADD;
}

fun calculationFromOperator(operator: Enum<ArithmeticOperators>):(BigDecimal, BigDecimal)->BigDecimal{
    val mathContext = MathContext(10)
    return when(operator) {
        ArithmeticOperators.ADD->{a,b->a.add(b)}
        ArithmeticOperators.SUBTRACT->{ a, b->a.subtract(b)}
        ArithmeticOperators.MULTIPLY->{a,b->a.multiply(b)}
        ArithmeticOperators.DIVIDE->{a,b->a.divide(b, mathContext)} // max 10 decimal places
        else -> {throw Exception("Not a valid enum: Must be an enum in ArithmeticOperators to be valid")}
    }
}
