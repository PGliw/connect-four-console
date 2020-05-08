package main

class GameEngine {
    var player = 1
    var state = Board(Array(6) { Array(7) { 0 } })

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
            print("Ruch $player: $movements> ")

            // make move
            val column = readLine()!!.toInt()

            // update state
            state = state.insert(column, player)

            // assess state
            winner = state.assess()
            if (winner > 0) break

            player = if (player == 1) 2 else 1
        }

        // show results
        when (winner) {
            0 -> println("Brak zwycięzców")
            in 1..2 -> println("Wygrywa: $winner")
        }
    }
}