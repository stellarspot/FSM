package sample

import agent.Agent
import agent.AgentAction
import agent.AgentSpace
import agent.Direction
import draw.draw
import fsm.core.BaseFSM
import fsm.core.FSM
import fsm.core.StateTransition
import maze.Field
import maze.FieldPosition
import maze.Position
import maze.RectangularMaze


fun main(args: Array<String>) {
    println("FSM Sample")

    val maze = RectangularMaze(5, 5, listOf(
            FieldPosition(Position(0, 0), Field.DOOR),
            FieldPosition(Position(4, 0), Field.WALL)))

    val fsm = getFSM()
    val agent = Agent(Position(2, 2), Direction.UP)
    val space = AgentSpace(agent, maze, fsm)
    space.moveAgent()
    space.moveAgent()
    space.moveAgent()

    draw("Maze", 800, 600, space)
}

// Simple FSM
// Go forward for empty cells and rotate right for walls
fun getFSM(): FSM<Field, AgentAction> {
    val fsm = BaseFSM<Field, AgentAction>()
    val initialState = fsm.initialState
    initialState.transitions.add(
            StateTransition(Field.EMPTY, initialState, AgentAction.FORWARD))
    initialState.transitions.add(
            StateTransition(Field.WALL, initialState, AgentAction.RIGHT))
    return fsm
}