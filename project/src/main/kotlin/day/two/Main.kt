package day.two

import java.io.File

/**
 * The solution reads the file line by line. The input follows the pattern "Direction, space, units" like 'forward 8'
 * For each line, it separates the direction and the units that the submarine will take. Then, depending on the
 * direction, calculate the route.
 */
fun main() {
    // Initialize variables
    var horizontal = 0
    var depth = 0

    // Reading file line by line
    val inputStream = File(object {}.javaClass.getResource("/day.two/input").toURI()).inputStream()

    inputStream.bufferedReader().forEachLine {
        val submarineCommand = it.split("\\s".toRegex())
        val direction = submarineCommand[0]
        val units = submarineCommand[1].toInt()
        when(direction) {
            Directions.FORWARD.direction -> horizontal += units
            Directions.DOWN.direction -> depth += units
            Directions.UP.direction -> depth -= units
        }
    }
    println("Horizontal -> $horizontal \nDepth -> $depth")
    println("Final result -> ${horizontal * depth}" )
}

enum class Directions(val direction: String) {
    FORWARD("forward"),
    DOWN("down"),
    UP("up")
}