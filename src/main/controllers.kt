package main

interface IPlayerController {
    val number: Int
    fun register(boardOwner: BoardOwner)
    fun makeMove()
}

class PhysicalPlayer(override val number: Int) : IPlayerController {
    private var boardOwner: BoardOwner? = null

    override fun register(boardOwner: BoardOwner) {
        this.boardOwner = boardOwner
    }

    override fun makeMove() {
        val boardOwner =
            boardOwner ?: throw NullPointerException("Game engine not registered (id null) in player $number")
        val board = boardOwner.provide()
        board.draw()
        println("Heurystyka: ${board.heuristicScore()}")
        print("Ruch $number: ${board.availableColumns()}> ")

        // make move
        val column = readLine()!!.toInt()
        boardOwner.update(board.insert(column, number))
    }
}

class MiniMaxAiPlayer(
    override val number: Int,
    private val searchDepth: Int = 0,
    private val isLogging: Boolean = false
) : IPlayerController {

    private var boardOwner: BoardOwner? = null

    override fun register(boardOwner: BoardOwner) {
        this.boardOwner = boardOwner
    }

    override fun makeMove() {
        val boardOwner =
            boardOwner ?: throw NullPointerException("Game engine not registered (id null) in player $number")
        val board = boardOwner.provide()
        val bestMoveWithScore = miniMaxIterativeStep(board, number, 0, searchDepth, isLogging)
        boardOwner.update(board.insert(bestMoveWithScore.first, number))
    }
}

class AlphaBetaAiPlayer(
    override val number: Int,
    private val searchDepth: Int = 0,
    private val isLogging: Boolean = false
) : IPlayerController {

    private var boardOwner: BoardOwner? = null

    override fun register(boardOwner: BoardOwner) {
        this.boardOwner = boardOwner
    }

    override fun makeMove() {
        val boardOwner =
            boardOwner ?: throw NullPointerException("Game engine not registered (id null) in player $number")
        val board = boardOwner.provide()
        val bestMoveWithScore =
            alphaBetaIterativeStep(board, number, 0, searchDepth, Int.MAX_VALUE, Int.MIN_VALUE, isLogging)
        boardOwner.update(board.insert(bestMoveWithScore.first, number))
    }
}