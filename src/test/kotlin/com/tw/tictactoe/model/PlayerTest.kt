package com.tw.tictactoe.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `should record move as pair of position and symbol`() {
        val player = Player("qasim", Symbol.X)

        player.recordMove(5)

        assertEquals(player.movesMade(), mapOf(5 to Symbol.X))
    }

    @Test
    fun `should be true if player has any winning combination`() {
        val player = Player("Qasim", Symbol.O)

        player.recordMove(1)
        player.recordMove(2)
        player.recordMove(3)

        assertTrue(player.hasWon())
    }

    @Test
    fun `should be false if player does has any winning combination`() {
        val player = Player("Qasim", Symbol.O)

        player.recordMove(1)
        player.recordMove(3)

        assertFalse(player.hasWon())
    }
}