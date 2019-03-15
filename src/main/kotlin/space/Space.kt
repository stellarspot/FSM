package space

import fsm.core.State
import fsm.core.FiniteStateMachine

interface Action
interface Input

interface Space<I : Input, A : Action> {
    fun getInput(): I
    fun doAction(action: A)

    fun passed(): Boolean
    fun failed(): Boolean
}

class SpaceRunner<I : Input, A : Action>(val fsm: FiniteStateMachine<I, A>,
                                         val space: Space<I, A>,
                                         val missedTransition: (state: State<I, A>, input: I) -> Unit) {

    private var finished = false
    var state = fsm.initialState

    fun step() {
        val input = space.getInput()

        for (transition in state.transitions) {
            if (transition.input == input) {
                val action = transition.action
                space.doAction(action)
                state = transition.nextState
                return
            }
        }
        finished = true
        missedTransition(state, input)
    }

    fun run() {
        while (!finished && !space.passed() && !space.failed()) {
            step()
        }
    }
}