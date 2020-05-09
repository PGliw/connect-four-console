package main.game.controllers.console

import main.algorithms.heuristics.possibleRcdHeuristics
import main.game.BoardOwner
import main.game.controllers.IPlayerController

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
        println("Heurystyka: ${board.possibleRcdHeuristics()}")
        print("Ruch $number: ${board.availableColumns()}> ")

        // make move
        val column = readLine()!!.toInt()
        boardOwner.update(board.insert(column, number))
    }
}