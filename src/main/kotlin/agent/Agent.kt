package agent

import fsm.core.Action
import maze.Maze
import maze.Field
import maze.Position

enum class AgentAction : Action {
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

class AgentSpace(var agent: Agent, val maze: Maze) {

    val traces = mutableListOf<Agent>()

    fun doAction(action: AgentAction) = {

        traces.add(agent)


        when (action) {
            AgentAction.FORWARD -> {
                val nextPosition = nextPosition()
                val nextField = getField(nextPosition)
                if (nextField != Field.WALL) {
                    agent = Agent(nextPosition, agent.direction)
                }
            }
            AgentAction.LEFT -> agent = Agent(agent.position, agent.direction.rotateLeft())
            AgentAction.RIGHT -> agent = Agent(agent.position, agent.direction.rotateRight())
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

    private fun getField(position: Position): Field = maze[position.x, position.y]

    fun nextField(): Field = getField(nextPosition())

    override fun toString() = buildString {
        for (j in (0 until maze.height).reversed()) {
            for (i in 0 until maze.width) {
                if (i == agent.position.x && j == agent.position.y) {
                    append("R")
                } else {
                    when (maze[i, j]) {
                        Field.EMPTY -> append('_')
                        Field.WALL -> append('W')
                        Field.DOOR -> append('D')
                    }
                }
            }
            append('\n')
        }
    }
}