package main

fun miniMax(
    unaffectedBoard: Board,
    move: Int,
    turn: Int,
    level: Int,
    maxLevel: Int,
    isLogging: Boolean
): Pair<Int, Int> {
    val board = unaffectedBoard.insert(move, turn)
    val nextTurn = if (turn == 1) 2 else 1

    val assessment = board.assess()

    val score: Int = when {

        // if terminal node then return actual assessment
        assessment != 0 -> {
            if (isLogging) {
                println()
                println("Move: $move | Level: $level | Turn: $turn | isLogging: $isLogging | Score A = $assessment")
                board.draw()
                println()
            }
            assessment
        }

        // if maximum search depth is exceeded then return value of the heuristic value
        level >= maxLevel -> {
            val hscore = board.heuristicScore()
            if (isLogging) {
                println()
                println("Move: $move | Level: $level | Turn: $turn | isLogging: $isLogging | Score H = $hscore")
                board.draw()
                println()
            }
            hscore
        }

        // else compute minmax foreach child-state of current state
        else -> miniMaxIterativeStep(board, nextTurn, level, maxLevel, isLogging).second
    }
    return Pair(move, score)
}

fun miniMaxIterativeStep(
    board: Board,
    turn: Int,
    level: Int,
    maxLevel: Int,
    isLogging: Boolean = false
): Pair<Int, Int> {
    val moveOptions = board.availableColumns()
    val childMovesWithScores = mutableListOf<Pair<Int, Int>>()
    for (childMove in moveOptions) {
        val result = miniMax(
            board,
            childMove,
            turn,
            level + 1,
            maxLevel,
            isLogging
        )
        childMovesWithScores.add(result)
    }
    return when (turn) {
        1 -> childMovesWithScores.maxBy { it.second } ?: throw IllegalArgumentException("max is null")
        2 -> childMovesWithScores.minBy { it.second } ?: throw IllegalArgumentException("min is null")
        else -> throw IllegalArgumentException("Turn number $turn is out of range [1, 2]")
    }
}

