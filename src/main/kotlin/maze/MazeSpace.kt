package maze

import space.Action
import fsm.core.FiniteStateMachine
import fsm.core.BaseFiniteStateMachine
import java.lang.RuntimeException

enum class MazeAction : Action {
    FORWARD,
    RIGHT,
    LEFT,
}

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    fun rotateRight(): Direction = when (this) {
        UP -> RIGHT
        RIGHT -> DOWN
        DOWN -> LEFT
        LEFT -> UP
    }

    fun rotateLeft(): Direction = when (this) {
        UP -> LEFT
        RIGHT -> DOWN
        DOWN -> RIGHT
        LEFT -> UP
    }
}

data class Agent(val position: Position, val direction: Direction)

class MazeSpace(var agent: Agent,
                val maze: Maze,
                val fsm: FiniteStateMachine<MazeField, MazeAction> = BaseFiniteStateMachine()) {

    val traces = mutableListOf<Agent>()

    var state = fsm.initialState

    fun moveAgent() {

        println("move agent")
        val input = nextField()

        for (transition in state.transitions) {
            if (transition.input == input) {
                val action = transition.action
                doAction(action)
                state = transition.nextState
                return
            }
        }

        throw RuntimeException("State: ${state.name} does not have transition" +
                " for input: $input")
    }

    fun doAction(action: MazeAction) {

        traces.add(agent)

        when (action) {
            MazeAction.FORWARD -> {
                val nextPosition = nextPosition()
                val nextField = getField(nextPosition)
                if (nextField != MazeField.WALL) {
                    agent = Agent(nextPosition, agent.direction)
                }
            }
            MazeAction.LEFT -> agent = Agent(agent.position, agent.direction.rotateLeft())
            MazeAction.RIGHT -> agent = Agent(agent.position, agent.direction.rotateRight())
        }
    }

    private fun nextPosition(): Position = Position(
            agent.position.x + when (agent.direction) {
                Direction.LEFT -> -1
                Direction.RIGHT -> 1
                else -> 0
            },
            agent.position.y + when (agent.direction) {
                Direction.UP -> 1
                Direction.DOWN -> -1
                else -> 0
            }
    )

    private fun getField(position: Position): MazeField = maze[position.x, position.y]

    fun nextField(): MazeField = getField(nextPosition())

    override fun toString() = buildString {
        for (j in (0 until maze.height).reversed()) {
            for (i in 0 until maze.width) {
                if (i == agent.position.x && j == agent.position.y) {
                    append("R")
                } else {
                    when (maze[i, j]) {
                        MazeField.EMPTY -> append('_')
                        MazeField.WALL -> append('W')
                        MazeField.DOOR -> append('D')
                    }
                }
            }
            append('\n')
        }
    }
}