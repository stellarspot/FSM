package space

import fsm.State
import fsm.FiniteStateMachine
import fsm.StateTransition

interface Action
interface Input

interface Space<I : Input, A : Action, T : Trace> {
    fun getInput(): I
    fun doAction(action: A)

    fun passed(): Boolean
    fun failed(): Boolean

    fun getTrace(): T
}

interface Trace

data class TraceItem<T : Trace>(val trace: T, val next: TraceItem<T>?)

data class FSMTrace<I : Input, A : Action>(val state: String,
                                           val input: I,
                                           val action: A,
                                           val nextState: String) : Trace

data class SpaceRunnerTrace<I : Input, A : Action, T : Trace>(val fsmTrace: FSMTrace<I, A>,
                                                              val spaceTrace: T) : Trace

class SpaceRunner<I : Input, A : Action, T : Trace>(val fsm: FiniteStateMachine<I, A>,
                                                    val space: Space<I, A, T>,
                                                    var trace: TraceItem<SpaceRunnerTrace<I, A, T>>? = null,
                                                    val missedTransition: (state: State<I, A>, input: I) -> Unit) {

    private var finished = false
    var state = fsm.initialState

    fun step() {
        val input = space.getInput()

        for (transition in state.transitions) {
            if (transition.input == input) {
                val action = transition.action
                val nextSate = transition.nextState
                trace(state, transition)
                space.doAction(action)
                state = nextSate
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

    private fun trace(state: State<I, A>, transition: StateTransition<I, A>) {
        val fsmTrace = FSMTrace(state.name,
                transition.input,
                transition.action,
                transition.nextState.name)
        val spaceTrace = space.getTrace()
        val runnerTrace = SpaceRunnerTrace(fsmTrace, spaceTrace)
        trace = TraceItem(runnerTrace, trace)
    }
}


fun <T : Trace> TraceItem<T>?.reverse(): TraceItem<T>? {
    fun reverse(traceItem: TraceItem<T>?, accum: TraceItem<T>? = null): TraceItem<T>? =
            if (traceItem == null) accum else reverse(traceItem.next, TraceItem(traceItem.trace, accum))

    return reverse(this)
}