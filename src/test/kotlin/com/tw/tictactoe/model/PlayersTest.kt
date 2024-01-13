package com.tw.tictactoe.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayersTest {
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var players: Players

    @BeforeEach
    fun setUp() {
        player1 = Player("Mohd", Symbol.O)
        player2 = Player("Qasim", Symbol.X)
        players = Players(player1, player2)
    }

    @Test
    fun `should record move for current player`() {
        players.recordMove(0)

        assertTrue(player1.movesMade().containsKey(0))
        assertFalse(player2.movesMade().containsKey(0))
    }

    @Test
    fun `should change turn after recording move`() {
        assertEquals(player1, players.currentPlayer)

        players.changeTurn()

        assertEquals(player2, players.currentPlayer)
    }

    @Test
    fun `should give all moves made by both players`() {
        players.recordMove(1)
        players.changeTurn()
        players.recordMove(3)

        assertEquals(mapOf(1 to Symbol.O, 3 to Symbol.X), players.movesMade())
    }
}