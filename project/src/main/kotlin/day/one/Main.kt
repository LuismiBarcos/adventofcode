package day.one

import java.io.File

fun main() {
    var count = 0
    var previousElement = -1
    var actualElement: Int

    val bufferedReader = File(object {}.javaClass.getResource("/day.one/input").toURI()).bufferedReader()

    bufferedReader.useLines { lines ->
        lines.forEach {
            actualElement = it.toInt()
            if ((previousElement != -1) && (actualElement > previousElement)) {
                count += 1
            }
            previousElement = actualElement
        }
    }
    println("Final count -> $count")
}