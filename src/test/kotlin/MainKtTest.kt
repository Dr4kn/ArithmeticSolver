import org.junit.jupiter.api.Assertions.assertEquals

class MainKtTest {

    @org.junit.jupiter.api.Test
    fun evaluateCalculation() {
        val numbers = floatArrayOf(12F, 4F, 2F)
        val operators = CharArray(numbers.size - 1)
        operators[0] = '-'
        operators[1] = 'x'
//        assertEquals(true, evaluateCalculation(operators, numbers, 4F))
    }

    @org.junit.jupiter.api.Test
    fun createOperatorVariations() {
        val numberOfOperators = 4
        val operators = charArrayOf('x', '-')
        val variations = ArrayList<CharArray>()

        for (i in operators) {
            for (j in operators) {
                variations.add(charArrayOf(i, j))
            }
        }
//        createOperatorVariations(numberOfOperators, operators)
    }
}