package maze

enum class Action {
    FORWARD,
    RIGHT,
    LEFT,
}

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
}

data class Agent(val position: Position, val direction: Direction)

class AgentSpace(var agent: Agent, val maze: Maze) {

    fun doAction(action: Action) {
        throw NotImplementedError()
    }

    fun nextField(): Field {
        val x = agent.position.x + when (agent.direction) {
            Direction.LEFT -> -1
            Direction.RIGHT -> 1
            else -> 0
        }

        val y = agent.position.y + when (agent.direction) {
            Direction.UP -> 1
            Direction.DOWN -> -1
            else -> 0
        }

        return maze[x, y]
    }

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