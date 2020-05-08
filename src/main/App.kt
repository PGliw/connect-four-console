package main


fun main() {
    val ge = GameEngine(PhysicalPlayer(1), AlphaBetaAiPlayer(2, 2, isLogging = true))
    // val ge = GameEngine(PhysicalPlayer(1), PhysicalPlayer(2))
    ge.start()
}