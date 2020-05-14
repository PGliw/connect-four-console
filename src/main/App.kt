package main

import main.algorithms.heuristics.totalStrikeHeuristics
import main.game.GameEngine
import main.game.controllers.AlphaBetaAiPlayer
import main.game.controllers.MiniMaxAiPlayer
import main.game.controllers.console.PhysicalPlayer


fun main() {
    val player1 = MiniMaxAiPlayer(1, 2, { totalStrikeHeuristics() })
    val player2 = MiniMaxAiPlayer(2, 2, { totalStrikeHeuristics() })
    val ge = GameEngine(player1, player2)
    ge.start()
    println("1. liczba ruchów: ${player1.decisionTimes.size}, średni czas: ${player1.decisionTimes.average()}")
    println("2. liczba ruchów: ${player1.decisionTimes.size}, średni czas: ${player1.decisionTimes.average()}")
}