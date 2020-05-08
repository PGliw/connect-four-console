package main

import kotlin.math.max
import kotlin.math.min

fun alphaBeta(
    unaffectedBoard: Board,
    move: Int,
    turn: Int,
    level: Int,
    maxLevel: Int,
    alpha: Int,
    beta: Int,
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
                println("Move: $move | Level: $level | Turn: $turn |  Score A = $assessment")
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
                println("Move: $move | Level: $level | Turn: $turn | Score H = $hscore")
                board.draw()
                println()
            }
            hscore
        }

        // else compute minmax foreach child-state of current state
        else -> alphaBetaIterativeStep(board, nextTurn, level, maxLevel, alpha, beta, isLogging).second
    }
    return Pair(move, score)
}

fun alphaBetaIterativeStep(
    board: Board,
    turn: Int,
    level: Int,
    maxLevel: Int,
    alpha: Int,
    beta: Int,
    isLogging: Boolean = false
): Pair<Int, Int> {
    val moveOptions = board.availableColumns()
    val childMovesWithScores = mutableListOf<Pair<Int, Int>>()
    var currentAlpha = alpha
    var currentBeta = beta

    for (childMove in moveOptions) {
        val result = alphaBeta(
            board,
            childMove,
            turn,
            level + 1,
            maxLevel,
            currentAlpha,
            currentBeta,
            isLogging
        )
        childMovesWithScores.add(result)

        // actual alpha-beta
        if (turn == 1) {
            val maxChild =
                childMovesWithScores.maxBy { it.second }?.second ?: throw IllegalArgumentException("max is null")
            currentAlpha = max(currentAlpha, maxChild)
            if (currentAlpha <= currentBeta) break
        } else if (turn == 2) {
            val minChild =
                childMovesWithScores.minBy { it.second }?.second ?: throw IllegalArgumentException("min is null")
            currentBeta = min(currentAlpha, minChild)
            if (currentBeta <= currentAlpha) break

        } else throw IllegalArgumentException("Turn number $turn is out of range [1, 2]")
    }

    return when (turn) {
        1 -> childMovesWithScores.maxBy { it.second } ?: throw IllegalArgumentException("max is null")
        2 -> childMovesWithScores.minBy { it.second } ?: throw IllegalArgumentException("min is null")
        else -> throw IllegalArgumentException("Turn number $turn is out of range [1, 2]")
    }
}