package com.tw.tictactoe.model

import com.tw.tictactoe.exception.GameOverException
import com.tw.tictactoe.exception.OutOfRangeException
import com.tw.tictactoe.exception.PreOccupiedException
import com.tw.tictactoe.model.Symbol.O
import com.tw.tictactoe.model.Symbol.X
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class GameTest {
    lateinit var p1: Player
    lateinit var p2: Player
    lateinit var players: Players
    lateinit var game: Game

    @BeforeEach
    fun setUp() {
        p1 = Player("Qasim", symbol = O)
        p2 = Player("Qasim", symbol = X)
        players = Players(p1, p2)
        game = Game(players)
    }

    @Test
    fun `should record move for current player and change the turn`() {
        game.makeMove(5, symbol)

        assertTrue { p1.movesMade().containsKey(5) }
        assertEquals(p2, players.currentPlayer)
    }

    @Test
    fun `should throw out of range exception when the position is not between 1 to 9`() {
        assertThrows<OutOfRangeException> { game.makeMove(10, symbol) }
    }

    @Test
    fun `should throw pre occupied exception when the position is already occupied`() {
        game.makeMove(4, symbol)
        assertThrows<PreOccupiedException> { game.makeMove(4, symbol) }
    }

    @Test
    fun `should throw game over exception when a player has won the game`() {
        game.makeMove(5, symbol)
        game.makeMove(2, symbol)
        game.makeMove(1, symbol)
        game.makeMove(3, symbol)
        game.makeMove(9, symbol)

        assertThrows<GameOverException> { game.makeMove(4, symbol) }
    }

    @Test
    fun `should throw game over exception when all moves are already made`() {
        game.makeMove(1, symbol)
        game.makeMove(5, symbol)
        game.makeMove(8, symbol)
        game.makeMove(4, symbol)
        game.makeMove(6, symbol)
        game.makeMove(9, symbol)
        game.makeMove(2, symbol)
        game.makeMove(3, symbol)
        game.makeMove(7, symbol)

        assertThrows<GameOverException> { game.makeMove(4, symbol) }
    }

    @Test
    fun `should return gameStatus with players, movesMade, currentPlayer, nextPlayer, isGameOver and winner`() {
        val gameStatus = game.gameStatus()

        assertEquals(listOf(p1, p2), gameStatus.players)
        assertEquals(mapOf(), gameStatus.moves)
        assertEquals(p1, gameStatus.currentPlayer)
        assertEquals(p2, gameStatus.nextPlayer)
        assertNull(gameStatus.winner)
        assertFalse { gameStatus.isGameOver }
    }
}