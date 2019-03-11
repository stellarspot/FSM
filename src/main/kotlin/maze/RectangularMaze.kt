package maze

data class Position(val x: Int, val y: Int)
data class FieldPosition(val position: Position, val field: Field)

class RectangularMaze(
        override val width: Int,
        override val height: Int,
        val agent: Agent,
        val positions: List<FieldPosition> = listOf()) : Maze {

    private val fields = Array(width) { Array(height) { Field.EMPTY } }
    private var currentAgent = agent
    private val trace = mutableListOf<Agent>()

    init {
        for (f in positions) {
            fields[f.position.x][f.position.y] = f.field
        }
    }

    override fun doAction(action: Action) = when (action) {
        Action.FORWARD -> {
            println("")
        }
        Action.LEFT -> println("")
        Action.RIGHT -> println("")
    }

    override fun nextField(): Field {
        val x = currentAgent.position.x + when (currentAgent.direction) {
            Direction.LEFT -> -1
            Direction.RIGHT -> 1
            else -> 0
        }

        val y = currentAgent.position.y + when (currentAgent.direction) {
            Direction.UP -> 1
            Direction.DOWN -> -1
            else -> 0
        }

        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Field.EMPTY
        }

        return fields[x][y]
    }

    override fun toString() = buildString {
        for (j in (0 until height).reversed()) {
            for (i in 0 until width) {

                if (i == currentAgent.position.x && j == currentAgent.position.y) {
                    append("R")
                } else {
                    when (fields[i][j]) {
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