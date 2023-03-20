package arithmeticOperatorSolver

enum class ArithmeticOperators {
    // this is also the order the operations have to occur in
    // otherwise the code doesn't work correctly unless you write this order separately or a parser
    MULTIPLY,
    DIVIDE,
    SUBTRACT,
    ADD;
}