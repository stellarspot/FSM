package sample

import agent.Agent
import agent.AgentSpace
import agent.Direction
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

    val agent = Agent(Position(2, 2), Direction.UP)
    val space = AgentSpace(agent, maze)

    draw("Maze", 800, 600, space)
}