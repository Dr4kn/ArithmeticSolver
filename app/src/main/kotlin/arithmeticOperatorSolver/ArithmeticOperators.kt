package arithmeticOperatorSolver

import java.math.BigDecimal

enum class ArithmeticOperators {
    // this is also the order the operations have to occur in
    // otherwise the code doesn't work correctly unless you write this order separately or a parser
    MULTIPLY,
    DIVIDE,
    SUBTRACT,
    ADD;
}
fun calculationFromOperator(operator: Enum<ArithmeticOperators>):(BigDecimal, BigDecimal)->BigDecimal{
    return when(operator) {
        ArithmeticOperators.ADD->{a,b->a.add(b)}
        ArithmeticOperators.SUBTRACT->{ a, b->a.subtract(b)}
        ArithmeticOperators.MULTIPLY->{a,b->a.multiply(b)}
        ArithmeticOperators.DIVIDE->{a,b->a.divide(b)}
        else -> {throw Exception("Not a valid enum: Must be an enum in ArithmeticOperators to be valid")}
    }
}
