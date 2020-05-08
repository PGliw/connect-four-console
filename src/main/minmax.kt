package main

/**
 * @return (p1 winning rows + columns + diagonals) - (p2 winning rows + columns + diagonals)
 * "winning row / column / diagonal" means that player p1 can still complete 4 items in this row / column / diagonal
 */
fun Board.heuristicScore(player: Int, opponent: Int): Int {
    val lines = mutableListOf<List<Int>>().apply{
        addAll(fields.columns())
        addAll(fields.rows())
        addAll(fields.diagonals())
    }

    // if player won then return +Inf
    lines.count { it.containsSeqenceOf(4, player) }.let { wonLines ->
        if(wonLines > 0) return Int.MAX_VALUE
    }

    // if player lost then return -Inf
    lines.count { it.containsSeqenceOf(4, opponent) }.let { lostLines ->
        if (lostLines >0) return Int.MIN_VALUE
    }

    // count winning rows, columns and diagonals for p1 player
    val playerWinningLines = lines.count { it.containsSeqenceOf(4, player, 0) }

    // count winning rows, columns and diagonals for p2 player
    val opponentWinningLines = lines.count { it.containsSeqenceOf(4, opponent, 0) }

    // subtract the results
    return playerWinningLines - opponentWinningLines
}
