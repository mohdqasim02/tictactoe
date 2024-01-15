package com.tw.tictactoe.model

import com.tw.tictactoe.exception.LobbyExpiredException
import com.tw.tictactoe.exception.LobbyFullException
import com.tw.tictactoe.exception.LobbyWaitingException
import com.tw.tictactoe.model.LobbyStatus.EXPIRED
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class LobbyTest{
    private lateinit var lobby: Lobby

    @BeforeEach
    fun setUp() {
        lobby = Lobby()
    }

    @Test
    fun `should add player when lobby is waiting`() {
        assertDoesNotThrow { lobby.addPlayer("Mohd") }
        assertDoesNotThrow { lobby.addPlayer("Qasim") }
    }

    @Test
    fun `should throw LobbyFullException when two players are already added`() {
        lobby.addPlayer("Mohd")
        lobby.addPlayer("Qasim")

        assertThrows<LobbyFullException> { lobby.addPlayer("Honu") }
    }

    @Test
    fun `should throw LobbyExpiredException when the game has already started`() {
        lobby.addPlayer("Mohd")
        lobby.addPlayer("Qasim")
        lobby.startGame()

        assertThrows<LobbyExpiredException> { lobby.addPlayer("Honu") }
    }

    @Test
    fun `should start game if 2 players are present and mark the lobby as Expired`() {
        lobby.addPlayer("Mohd")
        lobby.addPlayer("Qasim")
        lobby.startGame()

        assertEquals(EXPIRED, lobby.status())
    }

    @Test
    fun `should throw LobbyWaitingException when 2 players are not present to start the game`() {
        assertThrows<LobbyWaitingException> { lobby.startGame() }
    }
}