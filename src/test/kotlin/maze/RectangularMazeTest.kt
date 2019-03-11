package maze

import org.junit.Test
import kotlin.test.assertEquals

class RectangularMazeTest {

    private val width = 3
    private val height = 3

    @Test
    fun testNextFieldUp() {

        assertEquals(Field.EMPTY,
                AgentSpace(
                        Agent(Position(1, 1), Direction.UP),
                        RectangularMaze(width, height,
                                listOf(FieldPosition(Position(1, 2), Field.EMPTY))
                        )
                ).nextField()
        )

        assertEquals(Field.WALL,
                AgentSpace(
                        Agent(Position(1, 1), Direction.UP),
                        RectangularMaze(width, height,
                                listOf(FieldPosition(Position(1, 2), Field.WALL))
                        )
                ).nextField()
        )

        assertEquals(Field.DOOR,
                AgentSpace(
                        Agent(Position(1, 1), Direction.UP),
                        RectangularMaze(width, height,
                                listOf(FieldPosition(Position(1, 2), Field.DOOR))
                        )
                ).nextField()
        )

        assertEquals(Field.WALL,
                AgentSpace(
                        Agent(Position(1, 2), Direction.UP),
                        RectangularMaze(width, height)
                ).nextField()
        )
    }

    @Test
    fun testNextFieldDown() {
        assertEquals(Field.EMPTY,
                AgentSpace(
                        Agent(Position(1, 1), Direction.DOWN),
                        RectangularMaze(width, height,
                                listOf(FieldPosition(Position(1, 0), Field.EMPTY))
                        )
                ).nextField()
        )

        assertEquals(Field.WALL,
                AgentSpace(
                        Agent(Position(1, 1), Direction.DOWN),
                        RectangularMaze(width, height,
                                listOf(FieldPosition(Position(1, 0), Field.WALL))
                        )
                ).nextField()
        )

        assertEquals(Field.DOOR,
                AgentSpace(
                        Agent(Position(1, 1), Direction.DOWN),
                        RectangularMaze(width, height,
                                listOf(FieldPosition(Position(1, 0), Field.DOOR))
                        )
                ).nextField()
        )

        assertEquals(Field.WALL,
                AgentSpace(
                        Agent(Position(1, 0), Direction.DOWN),
                        RectangularMaze(width, height)
                ).nextField()
        )
    }

    @Test
    fun testNextFieldRight() {
        assertEquals(Field.EMPTY,
                AgentSpace(
                        Agent(Position(1, 1), Direction.RIGHT),
                        RectangularMaze(width, height,
                                listOf(FieldPosition(Position(2, 1), Field.EMPTY))
                        )
                ).nextField()
        )

        assertEquals(Field.WALL,
                AgentSpace(
                        Agent(Position(1, 1), Direction.RIGHT),
                        RectangularMaze(width, height,
                                listOf(FieldPosition(Position(2, 1), Field.WALL))
                        )
                ).nextField()
        )

        assertEquals(Field.DOOR,
                AgentSpace(
                        Agent(Position(1, 1), Direction.RIGHT),
                        RectangularMaze(width, height,
                                listOf(FieldPosition(Position(2, 1), Field.DOOR))
                        )
                ).nextField()
        )

        assertEquals(Field.WALL,
                AgentSpace(
                        Agent(Position(2, 1), Direction.RIGHT),
                        RectangularMaze(width, height)
                ).nextField()
        )
    }

    @Test
    fun testNextFieldLeft() {
        assertEquals(Field.EMPTY,
                AgentSpace(
                        Agent(Position(1, 1), Direction.LEFT),
                        RectangularMaze(width, height,
                                listOf(FieldPosition(Position(0, 1), Field.EMPTY))
                        )
                ).nextField()
        )

        assertEquals(Field.WALL,
                AgentSpace(
                        Agent(Position(1, 1), Direction.LEFT),
                        RectangularMaze(width, height,
                                listOf(FieldPosition(Position(0, 1), Field.WALL))
                        )
                ).nextField()
        )

        assertEquals(Field.DOOR,
                AgentSpace(
                        Agent(Position(1, 1), Direction.LEFT),
                        RectangularMaze(width, height,
                                listOf(FieldPosition(Position(0, 1), Field.DOOR))
                        )
                ).nextField()
        )

        assertEquals(Field.WALL,
                AgentSpace(
                        Agent(Position(0, 1), Direction.LEFT),
                        RectangularMaze(width, height)
                ).nextField()
        )
    }
}