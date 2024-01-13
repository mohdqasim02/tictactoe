package com.tw.tictactoe.model

enum class WinningCombination(private val combination: List<Int>) {
    FIRST_ROW(listOf(1, 2, 3)),
    SECOND_ROW(listOf(4, 5, 6)),
    THIRD_ROW(listOf(7, 8, 9)),
    FIRST_COLUMN(listOf(1, 4, 7)),
    SECOND_COLUMN(listOf(2, 5, 8)),
    THIRD_COLUMN(listOf(3, 6, 9)),
    FIRST_DIAGONAL(listOf(1, 5, 9)),
    SECOND_DIAGONAL(listOf(3, 5, 7));

    fun hasWinningCombination(playerMoves: Set<Int>): Boolean {
        return this.combination.all { playerMoves.contains(it) }
    }

    companion object {
        fun hasAnyWinningCombination(playerMoves: Set<Int>): Boolean {
            return WinningCombination.entries.any { it.hasWinningCombination(playerMoves) }
        }
    }
}