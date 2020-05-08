package main

class Board(private val fields: Array<Array<Int>>) {
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

    fun assess(): Int {
        // list of diagonals
        val diagonals = fields.diagonals()
        val rows = fields.rows()
        val columns = fields.columns()
        val allDirections = mutableListOf<List<Int>>().apply {
            addAll(diagonals)
            addAll(rows)
            addAll(columns)
        }

        for (player in 1..2) {
            if (allDirections.any { list -> list.containsSeqenceOf(player, 4) }) {
                return player
            }
        }

        return 0
    }

    private fun isRowAvailable(row: Int) = fields[row].contains(0)

    fun draw() = fields.draw()
}