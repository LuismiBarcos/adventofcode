package day.four

/**
 *@author Luis Miguel Barcos
 */
class Board(
    private val matrix: List<IntArray> = arrayListOf(
        IntArray(5) { 0 },
        IntArray(5) { 0 },
        IntArray(5) { 0 },
        IntArray(5) { 0 },
        IntArray(5) { 0 },
    )
) {
    fun setPositionValue(row: Int, column: Int, value: Int) {
        matrix[row][column] = value
    }

    fun totalScore(): Int {
        var sum = 0
        for (row in matrix) {
            for (col in row) {
                if(col != -1) {
                    sum += col
                }
            }
        }
        return sum
    }

    fun contains(value: Int): Boolean {
        var found = false
        var row = 0
        var column = 0
        while (!found && row <= 4) {
            while (!found && column <= 4) {
                if (matrix[row][column] == value) {
                    found = true
                    matrix[row][column] = -1
                }
                column++
            }
            column = 0
            row++
        }
        return found
    }

    fun hasBingo(): Boolean {
        // The matrix must contains in the first row or in the first column a -1 value. If not, it not contains bingo
        var row = 0
        var column = 0
        var exit = false
        var hasBingo = false
        while (!exit && !hasBingo) {
            val strikethroughNumber = findStrikethroughNumber(row, column)
            if(strikethroughNumber.value == -2) {
                exit = true
            } else {
                if(isRowCrossed(strikethroughNumber.rowIndex)) {
                    hasBingo = true
                }
                if(isColumnCrossed(strikethroughNumber.columnIndex)) {
                    hasBingo = true
                }
            }
            if(strikethroughNumber.rowIndex >= 4 && strikethroughNumber.columnIndex >= 4) {
                exit = true
            } else if (strikethroughNumber.columnIndex >= 4) {
                row = strikethroughNumber.rowIndex + 1
                column = 0
            } else {
                row = strikethroughNumber.rowIndex
                column = strikethroughNumber.columnIndex + 1
            }
        }
        return hasBingo
    }

    private fun findStrikethroughNumber(rowToStart: Int, columnToStart: Int): BoardPoint {
        var founded = false
        var row = rowToStart
        var column = columnToStart
        var boardPoint = BoardPoint()
        while (!founded && row <= 4) {
            while (!founded && column <= 4) {
                if(matrix[row][column] == -1) {
                    founded = true
                    boardPoint = BoardPoint(row, column, -1)
                }
                column++
            }
            column = 0
            row++
        }
//        val pointValue = if (founded) -1 else -2
        return boardPoint
//        return BoardPoint(row, column, pointValue)
    }

    private fun isRowCrossed(currentRow: Int): Boolean {
        var column = 0
        var numberCrossed = true
        while (numberCrossed && column <= 4) {
            if(matrix[currentRow][column] != -1) {
                numberCrossed = false
            }
            column++
        }
        return numberCrossed
    }

    private fun isColumnCrossed(currentColumn: Int): Boolean {
        var row = 0
        var numberCrossed = true
        while (numberCrossed && row <= 4) {
            if(matrix[row][currentColumn] != -1) {
                numberCrossed = false
            }
            row++
        }
        return numberCrossed
    }

    private data class BoardPoint(val rowIndex: Int = 0, val columnIndex: Int = 0, var value: Int = -2)
}
