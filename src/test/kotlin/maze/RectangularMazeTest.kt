package maze

import org.junit.Test
import kotlin.test.assertEquals

class RectangularMazeTest {

    private val width = 3
    private val height = 3

    @Test
    fun testNextFieldUp() {

        assertEquals(Field.EMPTY,
                RectangularMaze(width, height,
                        Agent(Position(1, 1), Direction.UP),
                        listOf(FieldPosition(Position(1, 2), Field.EMPTY))
                ).nextField()
        )

        assertEquals(Field.WALL,
                RectangularMaze(width, height,
                        Agent(Position(1, 1), Direction.UP),
                        listOf(FieldPosition(Position(1, 2), Field.WALL))
                ).nextField()
        )

        assertEquals(Field.DOOR,
                RectangularMaze(width, height,
                        Agent(Position(1, 1), Direction.UP),
                        listOf(FieldPosition(Position(1, 2), Field.DOOR))
                ).nextField()
        )

        assertEquals(Field.EMPTY,
                RectangularMaze(width, height,
                        Agent(Position(1, 2), Direction.UP)
                ).nextField()
        )
    }

    @Test
    fun testNextFieldDown() {
        assertEquals(Field.EMPTY,
                RectangularMaze(width, height,
                        Agent(Position(1, 1), Direction.DOWN),
                        listOf(FieldPosition(Position(1, 0), Field.EMPTY))
                ).nextField()
        )

        assertEquals(Field.WALL,
                RectangularMaze(width, height,
                        Agent(Position(1, 1), Direction.DOWN),
                        listOf(FieldPosition(Position(1, 0), Field.WALL))
                ).nextField()
        )

        assertEquals(Field.DOOR,
                RectangularMaze(width, height,
                        Agent(Position(1, 1), Direction.DOWN),
                        listOf(FieldPosition(Position(1, 0), Field.DOOR))
                ).nextField()
        )

        assertEquals(Field.EMPTY,
                RectangularMaze(width, height,
                        Agent(Position(1, 0), Direction.DOWN)
                ).nextField()
        )
    }

    @Test
    fun testNextFieldRight() {
        assertEquals(Field.EMPTY,
                RectangularMaze(width, height,
                        Agent(Position(1, 1), Direction.RIGHT),
                        listOf(FieldPosition(Position(2, 1), Field.EMPTY))
                ).nextField()
        )

        assertEquals(Field.WALL,
                RectangularMaze(width, height,
                        Agent(Position(1, 1), Direction.RIGHT),
                        listOf(FieldPosition(Position(2, 1), Field.WALL))
                ).nextField()
        )

        assertEquals(Field.DOOR,
                RectangularMaze(width, height,
                        Agent(Position(1, 1), Direction.RIGHT),
                        listOf(FieldPosition(Position(2, 1), Field.DOOR))
                ).nextField()
        )

        assertEquals(Field.EMPTY,
                RectangularMaze(width, height,
                        Agent(Position(2, 1), Direction.RIGHT)
                ).nextField()
        )
    }

    @Test
    fun testNextFieldLeft() {
        assertEquals(Field.EMPTY,
                RectangularMaze(width, height,
                        Agent(Position(1, 1), Direction.LEFT),
                        listOf(FieldPosition(Position(0, 1), Field.EMPTY))
                ).nextField()
        )

        assertEquals(Field.WALL,
                RectangularMaze(width, height,
                        Agent(Position(1, 1), Direction.LEFT),
                        listOf(FieldPosition(Position(0, 1), Field.WALL))
                ).nextField()
        )

        assertEquals(Field.DOOR,
                RectangularMaze(width, height,
                        Agent(Position(1, 1), Direction.LEFT),
                        listOf(FieldPosition(Position(0, 1), Field.DOOR))
                ).nextField()
        )

        assertEquals(Field.EMPTY,
                RectangularMaze(width, height,
                        Agent(Position(0, 1), Direction.LEFT)
                ).nextField()
        )
    }
}