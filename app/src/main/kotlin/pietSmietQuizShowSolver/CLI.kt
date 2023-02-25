package pietSmietQuizShowSolver

class CLI {
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


    fun buildSolutionArray(solutionsList: ArrayList<CharArray>): ArrayList<Array<Any>> {
        val solutionArrays = ArrayList<Array<Any>>()
        // size of number + size of operators (number - 1) and the last two
        val calculation: Array<Any> = Array(numbers.size * 2 + 1) { 0 }
        var i: Int
        var nIndex: Int
        var oIndex: Int
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
}