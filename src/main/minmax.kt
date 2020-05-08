package main

fun minimax(unaffectedBoard: Board, move: Int, player: Int, opponent: Int, level: Int, maxLevel: Int): Pair<Int, Int> {
    val board = unaffectedBoard.insert(move, player)
    val moveOptions = board.availableColumns()

    val score: Int = when {
        // if terminal node then return actual assessment
        moveOptions.isEmpty() -> board.assess(player, opponent)

        // if maximum search depth is exceeded then return value of the heuristic value
        level > maxLevel -> board.heuristicScore(player, opponent)

        // else compute minmax foreach child-state of current state
        else -> {
            val childMovesWithScores = mutableListOf<Pair<Int, Int>>()
            for (childMove in moveOptions) {
                val result = minimax(
                    board,
                    childMove,
                    opponent,
                    player,
                    level + 1,
                    maxLevel
                )
                childMovesWithScores.add(result)
            }
            when (player) {
                1 -> childMovesWithScores.maxBy { it.second }?.second ?: throw IllegalArgumentException("max is null")
                else -> childMovesWithScores.minBy { it.second }?.second
                    ?: throw IllegalArgumentException("min is null")
            }
        }
    }
    return Pair(move, score)
}

