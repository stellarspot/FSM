package maze

enum class Field {
    EMPTY,
    WALL,
    DOOR,
}

interface Maze {
    val width: Int
    val height: Int

    operator fun get(x: Int, y: Int): Field
}