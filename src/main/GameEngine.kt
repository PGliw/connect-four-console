package main

class GameEngine
    (
    private val player1Controller: IPlayerController,
    private val player2Controller: IPlayerController
) {
    private var player = 1
    var board = Board(Array(6) { Array(7) { 0 } })
    private val opponent
        get() = if (player == 1) 2 else 1
    var availableMovements = listOf<Int>()

    init {
        player1Controller.register(this)
        player2Controller.register(this)
    }

    // starts game loop
    fun start() {
        var winner = 0
        while (true) {
            // show state
            board.draw()

            // if the board is full or somebody won then break
            availableMovements = board.availableColumns()
            if (winner != 0 || availableMovements.isEmpty()) {
                break
            }

            val currentPlayerController = if (player == 1) player1Controller else player2Controller
            currentPlayerController.makeMove()

            // assess state
            val score = board.assess(player, opponent)
            winner = when (score) {
                Int.MAX_VALUE -> player
                Int.MIN_VALUE -> opponent
                else -> 0
            }

            player = opponent
        }

        // show result1s
        when (winner) {
            0 -> println("Brak zwycięzców")
            else -> println("Wygrywa: $winner")
        }
    }
}