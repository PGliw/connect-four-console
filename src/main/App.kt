package main

import main.algorithms.heuristics.possibleRcdHeuristics
import main.algorithms.heuristics.totalStrikeHeuristics
import main.game.GameEngine
import main.game.controllers.AlphaBetaAiPlayer
import main.game.controllers.PhysicalPlayer


fun main() {
    val ge = GameEngine(
        PhysicalPlayer(1),
        AlphaBetaAiPlayer(2, 5, { totalStrikeHeuristics() })
    )
    ge.start()
}