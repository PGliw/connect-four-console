package main

class Board(val fields: Array<Array<Int>>) {
    fun availableColumns(): List<Int> {
        val allColumns = fields.columns().withIndex()
        val availableColumns = allColumns.filter { it.value.contains(0) }
        return availableColumns.map { it.index }
    }

    fun insert(column: Int, player: Int): Board {
        val columns = fields.columns()
        val index = columns[column].indexOfLast { it == 0 }
        val newFields = fields.copyOf()
        newFields[index][column] = player
        return Board(newFields)
    }

    fun assess(player: Int, opponent: Int): Int {
        // list of diagonals
        val diagonals = fields.diagonals()
        val rows = fields.rows()
        val columns = fields.columns()
        val allDirections = mutableListOf<List<Int>>().apply {
            addAll(diagonals)
            addAll(rows)
            addAll(columns)
        }

        return when {
            allDirections.any { list -> list.containsSeqenceOf(4, player) } -> Int.MAX_VALUE // player wins opponent loses
            allDirections.any { list -> list.containsSeqenceOf(4, opponent) } -> Int.MIN_VALUE  // opponent wins player loses
            else -> 0 // nobody is winning at the time
        }
    }

    private fun isRowAvailable(row: Int) = fields[row].contains(0)

    fun draw() = fields.draw()
}