package maze

data class FieldPosition(val position: Position, val field: Field)

class RectangularMaze(
        override val width: Int,
        override val height: Int,
        val positions: List<FieldPosition> = listOf()) : Maze {

    private val fields = Array(width) { Array(height) { Field.EMPTY } }

    init {
        for (f in positions) {
            fields[f.position.x][f.position.y] = f.field
        }
    }

    override fun get(x: Int, y: Int): Field {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Field.WALL
        }
        return fields[x][y]
    }

    override fun toString() = buildString {
        for (j in (0 until height).reversed()) {
            for (i in 0 until width) {
                when (fields[i][j]) {
                    Field.EMPTY -> append('_')
                    Field.WALL -> append('W')
                    Field.DOOR -> append('D')
                }
            }
            append('\n')
        }
    }
}