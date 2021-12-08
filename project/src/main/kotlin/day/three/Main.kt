package day.three

import java.io.File

/**
 * Assuming that the number of digits per row in the file is 12.
 */
fun main() {
    val gamma = IntArray(12) { 0 }
    val epsilon = IntArray(12) { 0 }

    // Reading file line by line
    val inputStream = File(object {}.javaClass.getResource("/day.three/input").toURI()).inputStream()

    inputStream.bufferedReader().forEachLine {
        it.forEachIndexed { index, bit ->
            when (bit) {
                '0' -> gamma[index] = gamma[index] - 1
                '1' -> gamma[index] = gamma[index] + 1
            }
        }
    }

    var gammaString = ""
    var epsilonString = ""

    gamma.forEach { gammaBit ->
        gammaString += if (gammaBit > 0) 1 else 0
        epsilonString += if (gammaBit > 0) 0 else 1
    }

    println("Gamma $gammaString. Decimal: ${gammaString.toInt(2)}\nEpsilon $epsilonString. Decimal: ${epsilonString.toInt(2)}")
    println("Final result: ${gammaString.toInt(2) * epsilonString.toInt(2)}")
}