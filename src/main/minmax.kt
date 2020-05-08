package main

fun miniMax(unaffectedBoard: Board, move: Int, turn: Int, level: Int, maxLevel: Int): Pair<Int, Int> {
    val board = unaffectedBoard.insert(move, turn)
    val moveOptions = board.availableColumns()

    val score: Int = when {
        // if terminal node then return actual assessment
        moveOptions.isEmpty() -> board.assess()

        // if maximum search depth is exceeded then return value of the heuristic value
        level > maxLevel -> board.heuristicScore()

        // else compute minmax foreach child-state of current state
        else -> miniMaxIterativeStep(board, turn, level, maxLevel).second
    }
    return Pair(move, score)
}

fun miniMaxIterativeStep(board: Board, turn: Int, level: Int, maxLevel: Int): Pair<Int, Int> {
    val moveOptions = board.availableColumns()
    val childMovesWithScores = mutableListOf<Pair<Int, Int>>()
    val nextTurn = if (turn == 1) 2 else 1
    for (childMove in moveOptions) {
        val result = miniMax(
            board,
            childMove,
            nextTurn,
            level + 1,
            maxLevel
        )
        childMovesWithScores.add(result)
    }
    return when (turn) {
        1 -> childMovesWithScores.maxBy { it.second } ?: throw IllegalArgumentException("max is null")
        2 -> childMovesWithScores.minBy { it.second } ?: throw IllegalArgumentException("min is null")
        else -> throw IllegalArgumentException("Turn number $turn is out of range [1, 2]")
    }
}

