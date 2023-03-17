package pietSmietQuizShowSolver

val operators = ArithmeticOperators.values()
// TODO refactor to use Big Decimal


class Solver(private val numbers: FloatArray, private val solution: Float) : ISolver {
    init {
        if (numbers.isEmpty()) {
            throw IllegalArgumentException("no number was provided")
        }
        if (numbers.size == 1) {
            throw IllegalArgumentException("can't calculate operators for a single number")
        }
    }

    private val amountOfOperators = numbers.size - 1


    override fun solver(): ArrayList<Array<Enum<ArithmeticOperators>>> {
        // could use an IntArray as CharArray representation, but the speed didn't matter
        // this way it is much easier to debug visually for me
        val possibleSolutions = ArrayList<Array<Enum<ArithmeticOperators>>>()

        for (operators in createOperatorVariations()) {
            if (evaluateCalculation(operators)) {
                possibleSolutions.add(operators)
            }
        }

        // FIXME is this exception actually needed?
        if (possibleSolutions.size == 0) {
            throw Exception("There is no possible solution for this combination")
        }

        return possibleSolutions
    }


    private fun createOperatorVariations(): ArrayList<Array<Enum<ArithmeticOperators>>> {
        val variations = ArrayList<Array<Enum<ArithmeticOperators>>>()
        // has to start with the maximum because the array is incremented from the back
        var currentPosition:Int = amountOfOperators - 1
        var currentOperator = 0
        val operatorVariations = IntArray(amountOfOperators) {0}

        while (true) {
            // if not last element only do it one time
            if (currentPosition < amountOfOperators - 1) {
                currentPosition++
            }
            else {
                operatorVariations[currentPosition] = currentOperator // checked positions
                // goes from the numerical representation of the operators to the char representations
                // you could evaluate the valid results here to not save every possible variation to do it later
                // this way it is easier to test
                variations.add(Array(amountOfOperators) { i -> operators[operatorVariations[i]] })

                // if not last element go to the next right one
                if (currentOperator < operators.size - 1) {
                    currentOperator++
                }
                else {
                    // goes one left for every position that is already done
                    while (true) {
                        currentPosition--
                        currentOperator = 0

                        // triggers when every position is done
                        if (currentPosition < 0) {
                            return variations
                        }
                        // checks if the previous position is already done
                        if (operatorVariations[currentPosition] != operators.size - 1) {
                            operatorVariations[currentPosition] = operatorVariations[currentPosition] + 1
                            break
                        }
                        // resets the finished line to the first operator
                        operatorVariations[currentPosition] = 0
                    }
                }
            }
        }
    }


    private fun evaluateCalculation(operatorsToCheck: Array<Enum<ArithmeticOperators>>): Boolean {
        val operatorList = operatorsToCheck.toMutableList()
        val numberList = numbers.toMutableList()

        var result:Float
        // could be done with a parser but tbh I didn't want to write one
        for (operatorToCheck in ArithmeticOperators.values()) {
            var i = 0

            // makes every calculation in the mathematically correct order until the only one number is left
            while (i < operatorList.size) {
                if (operatorToCheck == operatorList[i]) {
                    // uses up the operators one at a time and also removes it numbering neighbors
                    result = calculationFromOperator(operatorList[i]).invoke(numberList[i], numberList[i + 1])
                    operatorList.removeAt(i)
                    numberList.removeAt(i)
                    numberList[i] = result

                    if (numberList.size == 1) {
                        return numberList[0] == solution
                    }
                }
                // necessary because when the same operator is behind it would jump over it
                else {
                    i++
                }
            }
        }
        // FIXME refactor in a way that this isn't needed anymore
        // this should NEVER happen (I think)
        throw IllegalStateException("Every operation should be checked")
    }


    private fun calculationFromOperator(operator: Enum<ArithmeticOperators>):(Float, Float)->Float {
        return when(operator) {
            ArithmeticOperators.ADD->{a,b->a+b}
            ArithmeticOperators.SUBTRACT->{ a, b->a-b}
            ArithmeticOperators.MULTIPLY->{a,b->a*b}
            ArithmeticOperators.DIVIDE->{a,b->a/b}
            else -> {throw Exception("Not a valid enum: Must be an enum in ArithmeticOperators to be valid")}
        }
    }
}