val orderOfOperations = arrayOf('x', '/', '+', '-')

fun main() {
//    val numbers = floatArrayOf(12F, 4F, 2F)
//    val solution = 4F
//    val allowedOperators = charArrayOf('x', '/', '+', '-')
    createOperatorVariations(3, charArrayOf('x', '+','-'))
}


fun createOperatorVariations(amountOfOperators: Int, operators: CharArray): ArrayList<CharArray> {
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
                     operatorVariations[currentPosition] = currentOperator
                }
            }
        }
    }
}


fun evaluateCalculation(operatorsToCheck: CharArray, numbers: FloatArray, solution: Float): Boolean {
    val operatorList = operatorsToCheck.toMutableList()
    val numberList = numbers.toMutableList()



    var result:Float
    for (operatorToCheck in orderOfOperations) {
        var i = 0
        // the operators are dropped out of the list when they are used therefore i
        while (i < operatorList.size) {
            if (operatorToCheck == operatorList[i]) {
                result = operatorFromChar(operatorList[i]).invoke(numberList[i], numberList[i + 1])
                operatorList.removeAt(i)
                numberList.removeAt(i)
                numberList[i] = result

                if (numberList.size == 1) {
                    return numberList[0] == solution
                }
            }
            i++
        }
    }
    return false
}


fun operatorFromChar(charOperator: Char):(Float, Float)->Float {
    return when(charOperator) {
        '+'->{a,b->a+b}
        '-'->{a,b->a-b}
        'x'->{a,b->a*b}
        '/'->{a,b->a/b}
        else -> throw Exception("That's not a supported operator")
    }
}