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
        println("Heurystyka: ${board.heuristicScore()}")
        print("Ruch $number: ${board.availableColumns()}> ")

        // make move
        val column = readLine()!!.toInt()
        boardOwner.update(board.insert(column, number))
    }
}

class MiniMaxAiPlayer(override val number: Int) : IPlayerController {
    private var boardOwner: BoardOwner? = null

    override fun register(boardOwner: BoardOwner) {
        this.boardOwner = boardOwner
    }

    override fun makeMove() {
        val boardOwner =
            boardOwner ?: throw NullPointerException("Game engine not registered (id null) in player $number")
        val board = boardOwner.provide()
        val bestMoveWithScore = miniMaxIterativeStep(board, number, 0, 5)
        boardOwner.update(board.insert(bestMoveWithScore.first, number))
    }
}