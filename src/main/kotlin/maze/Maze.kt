package maze

import space.Input

enum class MazeField : Input {
    EMPTY,
    WALL,
    DOOR,
}

data class Position(val x: Int, val y: Int)

interface Maze {
    val width: Int
    val height: Int

    operator fun get(x: Int, y: Int): MazeField
}