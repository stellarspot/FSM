package space

interface Action
interface Input

interface Space<I : Input, A : Action> {
    fun getInput(): I
    fun doAction(action: A)

    fun passed(): Boolean
    fun failed(): Boolean
}

