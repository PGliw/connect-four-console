package main

interface IPlayerController {
    val number: Int
    fun register(gameEngine: GameEngine)
    fun makeMove()
}

class PhysicalPlayer(override val number: Int) : IPlayerController {
    private val opponent = if (number == 1) 2 else 1
    private var gameEngine: GameEngine? = null

    override fun register(gameEngine: GameEngine) {
        this.gameEngine = gameEngine
    }

    override fun makeMove() {
        val gameEngine =
            gameEngine ?: throw NullPointerException("Game engine not registered (id null) in player $number")
        println("Twoja heurystyka: ${gameEngine.board.heuristicScore(number, opponent)}")
        print("Ruch $number: ${gameEngine.availableMovements}> ")

        // make move
        val column = readLine()!!.toInt()
        gameEngine.board = gameEngine.board.insert(column, number)
    }
}

class MiniMaxAiPlayer(override val number: Int) : IPlayerController {
    private var gameEngine: GameEngine? = null
    private val opponent = if (number == 1) 2 else 1

    override fun register(gameEngine: GameEngine) {
        this.gameEngine = gameEngine
    }

    override fun makeMove() {
        val gameEngine = gameEngine ?: throw NullPointerException("Game engine not registered (id null) in player $number")
//        TODO fix
//        val children = minimaxInductiveStep(gameEngine.board, number, opponent, 0, 5)
//        val move = children.maxBy { it.second }?.first ?: throw NullPointerException("Max child move not found in player $number")
//        gameEngine.board = gameEngine.board.insert(move, number)
    }
}