package day.four

import java.io.File

/**
 *
 */
fun main() {
    val boardsList = mutableListOf<Board>()
    //Reading the file
    val fileText = File(object {}.javaClass.getResource("/day.four/input").toURI()).readText()
    val lines = fileText.split("\n".toRegex())

    // LOADING DATA
    // Split first line by commas and convert String to Int to get the initial random numbers
    val randomNumbers = lines.first().split(",".toRegex()).map { it.toInt() }

    // Obviate first and second line with random numbers and create the boards
    var board = Board()
    var row = 0
    lines.subList(2, lines.size).forEach {
        if (it == "") {
            boardsList.add(board)
            row = 0
            board = Board()
        } else {
            it.split("\\s".toRegex())
            .filter { string -> string != "" }
            .map { number -> number.toInt() }
            .forEachIndexed { column, number ->
                board.setPositionValue(row, column, number)
            }
            row++
        }
    }

    // PLAYING BINGO
    var bingo = false
    var numberCounter = 0
    var boardWithBingo = Board()
    var lastNumber = 0
    while(!bingo && numberCounter <= randomNumbers.size) {
        var boardsIndex = 0
        while(!bingo && boardsIndex < boardsList.size) {
            boardsList[boardsIndex].contains(randomNumbers[numberCounter])
            if(boardsList[boardsIndex].hasBingo()) {
                boardWithBingo = boardsList[boardsIndex]
                bingo = true
                lastNumber = randomNumbers[numberCounter]
            }
            boardsIndex++
        }
        numberCounter++
    }
    println("Final score -> ${boardWithBingo.totalScore() * lastNumber}")
}