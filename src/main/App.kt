package main

import main.algorithms.heuristics.totalStrikeHeuristics
import main.game.GameEngine
import main.game.controllers.AlphaBetaAiPlayer
import main.game.controllers.console.PhysicalPlayer


fun main() {
    val ge = GameEngine(
        AlphaBetaAiPlayer(1, 4, { totalStrikeHeuristics() }),
        PhysicalPlayer(2)
    )
    ge.start()
}