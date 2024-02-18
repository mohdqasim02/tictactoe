package com.tw.tictactoe.controller

import com.tw.tictactoe.model.*
import com.tw.tictactoe.model.LobbyStatus.WAITING
import com.tw.tictactoe.model.Symbol.O
import com.tw.tictactoe.model.Symbol.X
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

    fun addPlayer(playerName: String): Pair<UUID, Symbol> {
        if(lobby.status() != WAITING) {
            createLobby()
        }

        val symbol = if(lobby.players.size == 0) X else O
        lobby.addPlayer(Player(playerName, symbol))

        return Pair(lobbyId, symbol)
    }

    fun getLobbyStatus(lobbyId: UUID) = lobbies[lobbyId]?.status()

    fun startGame(lobbyId: UUID) {
        if(games.containsKey(lobbyId)) return

        val lobby = lobbies[lobbyId]
        val game = lobby?.startGame()

        games[lobbyId] = game!!
    }

    fun makeMove(gameId: UUID, position: Int, symbol: Symbol) {
        games[gameId]?.makeMove(position, symbol)
    }

    fun getGameStatus(gameId: UUID): GameStatus {
        return games[gameId]!!.gameStatus()
    }
}