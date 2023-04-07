package arithmeticOperatorSolver

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class SolverTest {
    @Nested
    inner class SolverAlgorithmTesting {
        @Test
        fun `build solution array`() {
            val numbers = arrayOf<Number>()
            val solution = 20F

            val exception = assertThrows(IllegalArgumentException::class.java) {
                Solver(numbers, solution).solver()
            }

            assertEquals(
                "no number was provided",
                exception.message
            )
        }

        // TODO should this return an error or give a true or false?
        @Test
        fun `solve for a single number`() {
            val numbers = arrayOf<Number>(20F)
            val solution = 20F

            val exception = assertThrows(IllegalArgumentException::class.java) {
                Solver(numbers, solution).solver()
            }

            assertEquals(
                "can't calculate operators for a single number",
                exception.message
            )
        }

        @Test
        fun `solve for two numbers`() {
            val numbers = arrayOf<Number>(1000F, 50F)
            val solution = 20F
            val solver = Solver(numbers, solution).solver()
            val symbols = arrayListOf(arrayOf(ArithmeticOperators.DIVIDE))
            assertArrayEquals(solver[0], symbols[0])
        }

        @Test
        fun `solve for three numbers`() {
            val numbers = arrayOf<Number>(10F, 6F, 15F)
            val solution = 19F
            val solver = Solver(numbers, solution).solver()
            val symbols = arrayListOf(arrayOf(ArithmeticOperators.SUBTRACT, ArithmeticOperators.ADD))
            assertArrayEquals(solver[0], symbols[0])
        }

        @Test
        fun `solve for four numbers`() {
            val numbers = arrayOf<Number>(25F, 8F, 12F, 3F)
            val solution = 21F
            val solver = Solver(numbers, solution).solver()
            val symbols = arrayListOf(
                arrayOf(
                    ArithmeticOperators.SUBTRACT,
                    ArithmeticOperators.ADD,
                    ArithmeticOperators.DIVIDE
                )
            )
            assertArrayEquals(solver[0], symbols[0])
        }

        @Test
        fun `solve for five numbers`() {
            val numbers = arrayOf<Number>(-10F, 15F, 15F, 5F, 13F)
            val solution = -5F
            val solver = Solver(numbers, solution).solver()
            val symbols = arrayListOf(
                arrayOf(
                    ArithmeticOperators.ADD,
                    ArithmeticOperators.ADD,
                    ArithmeticOperators.DIVIDE,
                    ArithmeticOperators.SUBTRACT
                )
            )
            assertArrayEquals(solver[0], symbols[0])
        }

        @Test
        fun `solve with multiple solutions`() {
            val numbers = arrayOf<Number>(12F, 12F, 12F, 12F)
            val solution = 145F
            val solver = Solver(numbers, solution).solver()
            val symbols1 = arrayOf(ArithmeticOperators.MULTIPLY, ArithmeticOperators.ADD, ArithmeticOperators.DIVIDE)
            val symbols2 = arrayOf(ArithmeticOperators.DIVIDE, ArithmeticOperators.ADD, ArithmeticOperators.MULTIPLY)
            assertArrayEquals(solver[0], symbols1)
            assertArrayEquals(solver[1], symbols2)
            assertTrue(solver[0].contentEquals(symbols1))
            assertTrue(solver[1].contentEquals(symbols2))
        }
    }


    @Nested
    inner class EdgeCases {
        @Test
        fun `to big for int`() {
            val numbers = arrayOf<Number>(2147483647, 1F)
            val solution = 2147483648
            val actual = Solver(numbers, solution).solver()
            val expected = arrayListOf(arrayOf(ArithmeticOperators.ADD))
            assertArrayEquals(expected[0], actual[0])
        }

        @Test
        fun `floating point only in number`() {
            val numbers = arrayOf<Number>(4F, 4)
            val solution = 8
            val solver = Solver(numbers, solution).solver()
            val symbols = arrayListOf(arrayOf(ArithmeticOperators.ADD))
            assertArrayEquals(symbols[0], solver[0])
        }

        @Test
        fun `floating-point only in answer`() {
            val numbers = arrayOf<Number>(4, 4)
            val solution = 8F
            val solver = Solver(numbers, solution).solver()
            val symbols = arrayListOf(arrayOf(ArithmeticOperators.ADD))
            assertArrayEquals(symbols[0], solver[0])
        }

        @Test
        fun `answer with decimal places`() {
            val numbers = arrayOf<Number>(4, 4)
            val solution = 8.00F
            val solver = Solver(numbers, solution).solver()
            val symbols = arrayListOf(arrayOf(ArithmeticOperators.ADD))
            assertArrayEquals(symbols[0], solver[0])
        }
    }

    @Nested
    inner class ErrorChecks {
        @Test
        fun `divide by zero`() {
            val numbers = arrayOf<Number>(10F, 0F, 8F, -800000F, 0F)
            val solution = 0F

            val exception = assertThrows(Exception::class.java) {
                Solver(numbers, solution).solver()
            }

            assertEquals(
                "Division undefined",
                exception.message
            )
        }

        @Test
        fun `divide by zero with short`() {
            val numbers = arrayOf<Number>(3F, 0F)
            val solution = 3F

            val exception = assertThrows(Exception::class.java) {
                Solver(numbers, solution).solver()
            }

            assertEquals(
                "Division by zero",
                exception.message
            )
        }


        @Test
        fun `no Solution Found`() {
            val numbers = arrayOf<Number>(10F, 10F)
            val solution = 30F
            val exception = assertThrows(Exception::class.java) {
                Solver(numbers, solution).solver()
            }

            assertEquals(
                "There is no possible solution for this combination",
                exception.message
            )
        }
    }
}