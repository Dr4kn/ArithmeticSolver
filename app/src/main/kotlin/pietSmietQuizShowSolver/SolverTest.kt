package pietSmietQuizShowSolver

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import kotlin.Exception

class SolverTest {

    @Test
    fun buildSolutionArray() {
        val numbers = FloatArray(0)
        val solution = 20F

        val exception = assertThrows(IllegalArgumentException::class.java) {
            Solver(numbers, solution).solver()
        }

        assertEquals("no number was provided",
            exception.message)
    }

    // FIXME should this return an error or give a true or false?
    @Test
    fun solveForASingleNumber() {
        val numbers = floatArrayOf(20F)
        val solution = 20F

        val exception = assertThrows(IllegalArgumentException::class.java) {
            Solver(numbers, solution).solver()
        }

        assertEquals("can't calculate operators for a single number",
            exception.message)
    }

    @Test
    fun solveForTwoNumbers() {
        val numbers = floatArrayOf(1000F, 50F)
        val solution = 20F
        val solver = Solver(numbers, solution).solver()
        val symbols = arrayListOf(arrayOf(ArithmeticOperators.DIVIDE))
        assertArrayEquals(solver[0], symbols[0])
    }
    @Test
    fun solveForThreeNumbers() {
        val numbers = floatArrayOf(10F, 6F, 15F)
        val solution = 19F
        val solver = Solver(numbers, solution).solver()
        val symbols = arrayListOf(arrayOf(ArithmeticOperators.SUBTRACT, ArithmeticOperators.ADD))
        assertArrayEquals(solver[0], symbols[0])
    }

    @Test
    fun solveForFourNumbers() {
        val numbers = floatArrayOf(25F, 8F, 12F, 3F)
        val solution = 21F
        val solver = Solver(numbers, solution).solver()
        val symbols = arrayListOf(arrayOf(ArithmeticOperators.SUBTRACT, ArithmeticOperators.ADD, ArithmeticOperators.DIVIDE))
        assertArrayEquals(solver[0], symbols[0])
    }

    @Test
    fun solveForFiveNumbers() {
        val numbers = floatArrayOf(-10F, 15F, 15F, 5F, 13F)
        val solution = -5F
        val solver = Solver(numbers, solution).solver()
        val symbols = arrayListOf(arrayOf(
            ArithmeticOperators.ADD,
            ArithmeticOperators.ADD,
            ArithmeticOperators.DIVIDE,
            ArithmeticOperators.SUBTRACT))
        assertArrayEquals(solver[0], symbols[0])
    }

    @Test
    fun solveWithMultipleSolutions() {
        val numbers = floatArrayOf(12F, 12F, 12F, 12F)
        val solution = 145F
        val solver = Solver(numbers, solution).solver()
        val symbols1 = arrayOf(ArithmeticOperators.MULTIPLY, ArithmeticOperators.ADD, ArithmeticOperators.DIVIDE)
        val symbols2 = arrayOf(ArithmeticOperators.DIVIDE, ArithmeticOperators.ADD, ArithmeticOperators.MULTIPLY)
        assertArrayEquals(solver[0], symbols1)
        assertArrayEquals(solver[1], symbols2)
        assertTrue(solver[0].contentEquals(symbols1))
        assertTrue(solver[1].contentEquals(symbols2))
    }

    @Test
    fun divideByZero() {
        val numbers = floatArrayOf(10F, 0F, 8F, -800000F, 0F)
        val solution = 0F
        assertTrue(Solver(numbers, solution).solver().isNotEmpty())
    }

    @Test
    fun divideByZeroShort() {
        val numbers = floatArrayOf(3F, 0F)
        val solution = 3F
        assertTrue(Solver(numbers, solution).solver().isNotEmpty())
    }


    // FIXME first refactor then write those two
    // TODO to small for int (32bit) make it use big decimal
//    @Test
//    fun toBigForInt() {
//        val numbers = floatArrayOf(2147483647F, 1F)
//        val solution = 2147483648F
//        val solver = Solver(numbers, solution).solver()
//        val exception = assertThrows(Exception::class.java) {
//            Solver(numbers, solution).solver()
//        }
//
//        assertEquals("Number is to big",
//            exception.message)
//    }

    @Test
    fun noSolutionFound() {
        val numbers = floatArrayOf(10F, 10F)
        val solution = 30F
        val exception = assertThrows(Exception::class.java) {
            Solver(numbers, solution).solver()
        }

        assertEquals("There is no possible solution for this combination",
            exception.message)
    }
}