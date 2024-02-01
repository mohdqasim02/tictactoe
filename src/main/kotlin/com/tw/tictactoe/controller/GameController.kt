package com.tw.tictactoe.controller

import com.tw.tictactoe.model.Game
import com.tw.tictactoe.model.GameStatus
import com.tw.tictactoe.model.Lobby
import com.tw.tictactoe.model.LobbyStatus.WAITING
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameController {
    private val games = mutableMapOf<UUID, Game>()
    private val lobbies = mutableMapOf<UUID, Lobby>()

    private lateinit var lobby: Lobby
    private lateinit var lobbyId: UUID

    init {
        createLobby()
    }

    private fun createLobby() {
        lobby = Lobby()
        lobbyId = UUID.randomUUID()
        lobbies[lobbyId] = lobby
    }

    fun addPlayer(playerName: String): UUID {
        if(lobby.status() != WAITING) {
            createLobby()
        }

        lobby.addPlayer(playerName)
        return lobbyId
    }

    fun getLobbyStatus(lobbyId: UUID) = lobbies[lobbyId]?.status()

    fun startGame(lobbyId: UUID) {
        if(games.containsKey(lobbyId)) return

        val lobby = lobbies[lobbyId]
        val game = lobby?.startGame()

        games[lobbyId] = game!!
    }

    fun makeMove(gameId: UUID, position: Int) {
        games[gameId]?.makeMove(position)
    }

    fun getGameStatus(gameId: UUID): GameStatus {
        return games[gameId]!!.gameStatus()
    }
}