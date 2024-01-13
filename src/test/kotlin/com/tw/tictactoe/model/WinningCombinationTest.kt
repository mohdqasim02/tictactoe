package com.tw.tictactoe.model

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class WinningCombinationTest {
    @Test
    fun `should be true if player moves has a Winning Combination`() {
        assertTrue(WinningCombination.hasAnyWinningCombination(setOf(1, 2, 3, 4, 5)))
    }

    @Test
    fun `should be false if player moves does not have all moves from winning combination`() {
        assertFalse(WinningCombination.hasAnyWinningCombination(setOf(1, 3, 4, 6)))
    }
}