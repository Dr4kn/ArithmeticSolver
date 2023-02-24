package pietSmietQuizShowSolver

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class SolverTest {

    @Test
    fun solution() {
    }

    @Test
    fun buildSolutionArray() {

    }

    @Test
    fun solver() {
        val numbers = floatArrayOf(10F, 6F, 15F)
        val solution = 19F
        val solver = Solver(numbers, solution).solver()
        val symbols = arrayListOf(charArrayOf('-', '+'))
        assertArrayEquals(solver[0], symbols[0])
    }

    @Test
    fun evaluateCalculation() {
    }

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }
}