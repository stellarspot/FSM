package sample

import maze.Agent
import maze.Direction
import maze.MazeAction
import maze.MazeSpace
import draw.draw
import space.SpaceRunner
import fsm.BaseFiniteStateMachine
import fsm.FiniteStateMachine
import fsm.StateTransition
import maze.MazeField
import maze.FieldPosition
import maze.Position
import maze.RectangularMaze


fun main(args: Array<String>) {
    println("FiniteStateMachine Sample")

    val maze = RectangularMaze(5, 5, listOf(
            FieldPosition(Position(0, 0), MazeField.DOOR),
            FieldPosition(Position(4, 0), MazeField.WALL)))

    val fsm = getFSM()
    val agent = Agent(Position(2, 2), Direction.UP)
    val space = MazeSpace(agent, maze)
    val spaceRunner = SpaceRunner(fsm, space) { state, input ->
        println("Missed transition state: ${state.name}, input: $input")
    }
    spaceRunner.step()
    spaceRunner.step()
    spaceRunner.step()

    draw("Maze", 800, 600, space)
}

// Simple FiniteStateMachine
// Go forward for empty cells and rotate right for walls
fun getFSM(): FiniteStateMachine<MazeField, MazeAction> {
    val fsm = BaseFiniteStateMachine<MazeField, MazeAction>()
    val initialState = fsm.initialState
    initialState.transitions.add(
            StateTransition(MazeField.EMPTY, initialState, MazeAction.FORWARD))
    initialState.transitions.add(
            StateTransition(MazeField.WALL, initialState, MazeAction.RIGHT))
    return fsm
}