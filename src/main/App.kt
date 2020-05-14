package main

import main.algorithms.heuristics.possibleRcdHeuristics
import main.algorithms.heuristics.totalStrikeHeuristics
import main.game.GameEngine
import main.game.controllers.AlphaBetaAiPlayer
import main.game.controllers.MiniMaxAiPlayer
import java.io.File


fun main() {
    val rows = mutableListOf("Algorytm;Max głębokość;Heurystyka;Liczba ruchów;Sredni czas")
    val iterations = 1..5
    val depths = 2..7
    for (iteration in iterations) {
        for (depth in depths) {
            val player1 = MiniMaxAiPlayer(1, depth, { totalStrikeHeuristics() })
            val player2 = MiniMaxAiPlayer(2, depth, { totalStrikeHeuristics() })
            val ge = GameEngine(player1, player2)
            val player = when (ge.start()) {
                1 -> player1
                else -> player2
            }
            rows.add("minimax;$depth;totalStrike;${player.decisionTimes.size};${player.decisionTimes.average()}")
        }
        for (depth in depths) {
            val player1 = MiniMaxAiPlayer(1, depth, { possibleRcdHeuristics() })
            val player2 = MiniMaxAiPlayer(2, depth, { possibleRcdHeuristics() })
            val ge = GameEngine(player1, player2)
            val player = when (ge.start()) {
                1 -> player1
                else -> player2
            }
            rows.add("minimax;$depth;possibleRcdHeuristics;${player.decisionTimes.size};${player.decisionTimes.average()}")
        }
        for (depth in depths) {
            val player1 = AlphaBetaAiPlayer(1, depth, { totalStrikeHeuristics() })
            val player2 = AlphaBetaAiPlayer(2, depth, { totalStrikeHeuristics() })
            val ge = GameEngine(player1, player2)
            val player = when (ge.start()) {
                1 -> player1
                else -> player2
            }
            rows.add("alfabeta;$depth;totalStrike;${player.decisionTimes.size};${player.decisionTimes.average()}")
        }
        for (depth in depths) {
            val player1 = AlphaBetaAiPlayer(1, depth, { possibleRcdHeuristics() })
            val player2 = AlphaBetaAiPlayer(2, depth, { possibleRcdHeuristics() })
            val ge = GameEngine(player1, player2)
            val player = when (ge.start()) {
                1 -> player1
                else -> player2
            }
            rows.add("alfabeta;$depth;possibleRcdHeuristics;${player.decisionTimes.size};${player.decisionTimes.average()}")
        }
    }

    File("results.txt").printWriter().use { out -> rows.forEach { out.println(it) } }

}