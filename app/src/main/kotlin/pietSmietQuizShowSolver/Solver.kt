package pietSmietQuizShowSolver

// TODO use enums instead
val orderOfOperations = charArrayOf('x', '/', '-', '+')
val operators = charArrayOf('x', '/', '+', '-')
// TODO refactor to use number instead of Floats


class Solver(private val numbers: FloatArray, private var solution: Float) : ISolver {
    private val amountOfOperators = numbers.size - 1

    internal fun solution() {
        printSolution(buildSolutionArray(solver()))
    }

    private fun printSolution(solutionsList: ArrayList<Array<Any>>) {
        val solutionString = StringBuilder()

        for (solution in solutionsList) {
            for (char in solution) {
                solutionString.append(char)
                solutionString.append(" ")
            }
            solutionString.appendLine()
        }
        println(solutionString.toString())
    }


    override fun buildSolutionArray(solutionsList: ArrayList<CharArray>): ArrayList<Array<Any>> {
        val solutionArrays = ArrayList<Array<Any>>()
        // size of number + size of operators (number - 1) and the last two
        val calculation: Array<Any> = Array(numbers.size * 2 + 1) { 0 }
        var i:Int
        var nIndex:Int
        var oIndex:Int
        // = and solution never change
        // same as numbers.size + operators.size
        calculation[numbers.size * 2 - 1] = '='
        // they are cast to int to look better
        calculation[numbers.size * 2] = solution.toInt()

        for (operators in solutionsList) {
            i = 0
            nIndex = 0
            oIndex = 0

            // because the solution list is one shorter this one is here
            calculation[i++] = numbers[nIndex++].toInt()

            while (i < numbers.size + operators.size) {
                calculation[i++] = operators[oIndex++]
                calculation[i++] = numbers[nIndex++].toInt()
            }
            solutionArrays.add(calculation)
        }
        return solutionArrays
    }


    override fun solver(): ArrayList<CharArray> {
        // could use an IntArray as CharArray representation, but the speed didn't matter
        // this way it is much easier to debug visually for me
        val possibleSolutions = ArrayList<CharArray>()

        for (operators in createOperatorVariations()) {
            if (evaluateCalculation(operators)) {
                possibleSolutions.add(operators)
            }
        }

        if (possibleSolutions.size == 0) {
            throw Exception("There is no possible solution for this combination")
        }

        return possibleSolutions
    }


    private fun createOperatorVariations(): ArrayList<CharArray> {
        val variations = ArrayList<CharArray>()
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
                variations.add(CharArray(amountOfOperators) { i -> operators[operatorVariations[i]] })

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


    private fun evaluateCalculation(operatorsToCheck: CharArray): Boolean {
        val operatorList = operatorsToCheck.toMutableList()
        val numberList = numbers.toMutableList()

        var result:Float
        // could be done with a parser but tbh I didn't want to write one
        for (operatorToCheck in orderOfOperations) {
            var i = 0

            // makes every calculation in the mathematically correct order until the only one number is left
            while (i < operatorList.size) {
                if (operatorToCheck == operatorList[i]) {
                    // uses up the operators one at a time and also removes it numbering neighbors
                    result = operatorFromChar(operatorList[i]).invoke(numberList[i], numberList[i + 1])
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
        return false
    }


    private fun operatorFromChar(charOperator: Char):(Float, Float)->Float {
        return when(charOperator) {
            '+'->{a,b->a+b}
            '-'->{a,b->a-b}
            'x'->{a,b->a*b}
            '/'->{a,b->a/b}
            else -> throw Exception("That's not a supported operator")
        }
    }
}

