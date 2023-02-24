package pietSmietQuizShowSolver

interface ISolver {
    fun buildSolutionArray(solutionsList: ArrayList<CharArray>): ArrayList<Array<Any>>
    fun solver(): ArrayList<CharArray>
}
