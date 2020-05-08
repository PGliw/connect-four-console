package main

class GameEngine {
    private var player = 1
    private var state = Board(Array(6) { Array(7) { 0 } })
    private val opponent
        get() = if (player == 1) 2 else 1

    // starts game loop
    fun start() {
        var winner = 0
        while (winner == 0) {
            // show state
            state.draw()

            // show available movements
            val movements = state.availableColumns()
            if (movements.isEmpty()) {
                break
            }
            println("Twoja heurystyka: ${state.heuristicScore(player, opponent)}")
            // println(state.heuristicScore(player, opponent))
            print("Ruch $player: $movements> ")

            // make move
            val column = readLine()!!.toInt()

            // update state
            state = state.insert(column, player)

            // assess state
            winner = state.assess()
            if (winner > 0) break

            player = opponent
        }

        // show results
        when (winner) {
            0 -> println("Brak zwycięzców")
            in 1..2 -> println("Wygrywa: $winner")
        }
    }
}