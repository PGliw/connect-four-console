package main


fun main() {
    val ge = GameEngine(PhysicalPlayer(1), MiniMaxAiPlayer(2))
    ge.start()
}