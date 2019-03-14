package sample

import draw.draw
import maze.Field
import maze.FieldPosition
import maze.Position
import maze.RectangularMaze


fun main(args: Array<String>) {
    println("FSM Sample")

    val maze = RectangularMaze(5, 5, listOf(
            FieldPosition(Position(0, 0), Field.DOOR),
            FieldPosition(Position(4, 0), Field.WALL)))

    println("maze:\n$maze")

    draw("Maze", 800, 600, maze)
}