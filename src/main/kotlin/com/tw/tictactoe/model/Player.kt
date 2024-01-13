package com.tw.tictactoe.model

class Player (val name: String, val symbol: Symbol){
    private val moves: MutableSet<Int> = HashSet()

    fun recordMove(position:Int) {
        moves.add(position)
    }

    fun hasWon(): Boolean {
        return WinningCombination.hasAnyWinningCombination(moves)
    }

    fun movesMade() = moves.associateWith { symbol }
}