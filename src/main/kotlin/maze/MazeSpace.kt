package maze

import space.Action
import space.Space

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
                val maze: Maze) : Space<MazeField, MazeAction> {

    val traces = mutableListOf<Agent>()

    private fun getField(position: Position): MazeField = maze[position.x, position.y]

    override fun getInput() = getField(nextPosition())

    override fun passed() = false

    override fun failed() = false

    override fun doAction(action: MazeAction) {

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