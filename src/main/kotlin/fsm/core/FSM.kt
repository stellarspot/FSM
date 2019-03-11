package fsm.core

interface Action
interface Input

interface FSM<I : Input, A : Action> {
    fun nextAction(input: I): A
}