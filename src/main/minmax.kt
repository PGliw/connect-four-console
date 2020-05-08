package main

fun minmax(board: Board, player: Int, opponent: Int, level: Int, maxLevel: Int): Int {
    val moveOptions = board.availableColumns()

    // if terminal node then return actual assessment
    if (moveOptions.isEmpty()) {
        return when (board.assess(player, opponent)) {
            player -> Int.MAX_VALUE
            opponent -> Int.MIN_VALUE
            else -> 0 // unresolved
        }
    }

    // if maximum search depth is exceeded then return value of the heuristic value
    if (level > maxLevel) return board.heuristicScore(player, opponent)

    // else compute minmax foreach child-state of current state
    val moveScores = mutableListOf<Int>()
    for (move in moveOptions) {
        val newBoard = board.insert(move, player)
        val score = minmax(newBoard, player, opponent, level + 1, maxLevel) // TODO tutaj chyba nie oponent tylko player
        moveScores.add(score)
    }
    return when (player) {
        1 -> moveScores.max() ?: throw IllegalArgumentException("max is null")
        else -> moveScores.min() ?: throw IllegalArgumentException("min is null")
    }

}