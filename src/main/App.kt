package main


fun main() {
    // val ge = GameEngine(PhysicalPlayer(1), MiniMaxAiPlayer(2))
    val ge = GameEngine(PhysicalPlayer(1), PhysicalPlayer(2))
    ge.start()
}