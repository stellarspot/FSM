package maze

data class FieldPosition(val position: Position, val field: MazeField)

class RectangularMaze(
        override val width: Int,
        override val height: Int,
        val positions: List<FieldPosition> = listOf()) : Maze {

    private val fields = Array(width) { Array(height) { MazeField.EMPTY } }

    init {
        for (f in positions) {
            fields[f.position.x][f.position.y] = f.field
        }
    }

    override fun get(x: Int, y: Int): MazeField {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return MazeField.WALL
        }
        return fields[x][y]
    }

    override fun toString() = buildString {
        for (j in (0 until height).reversed()) {
            for (i in 0 until width) {
                when (fields[i][j]) {
                    MazeField.EMPTY -> append('_')
                    MazeField.WALL -> append('W')
                    MazeField.DOOR -> append('D')
                }
            }
            append('\n')
        }
    }
}