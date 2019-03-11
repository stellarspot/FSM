package maze

enum class Field {
    EMPTY,
    WALL,
    DOOR,
}

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

interface Maze {
    val width: Int
    val height: Int

    fun doAction(action: Action)
    fun nextField(): Field
}