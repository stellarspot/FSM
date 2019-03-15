package sample

import draw.draw
import fsm.BaseFiniteStateMachine
import fsm.FiniteStateMachine
import fsm.StateTransition
import maze.*
import space.SpaceRunner
import space.SpaceRunnerTrace
import space.TraceItem
import space.reverse


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
    spaceRunner.step()

    dumpTrace(spaceRunner.trace)

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

fun dumpTrace(trace: TraceItem<SpaceRunnerTrace<MazeField, MazeAction, MazeTrace>>?) {

    var step = 0
    var t = trace.reverse()

    while (t != null) {
        println("--- step: ${++step} ---")
        val fsmTrace = t.trace.fsmTrace
        val spaceTrace = t.trace.spaceTrace
        println("space: $spaceTrace")
        println("fsm  : $fsmTrace")
        t = t.next
    }
}