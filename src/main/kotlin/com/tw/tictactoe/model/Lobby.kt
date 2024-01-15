package com.tw.tictactoe.model

import com.tw.tictactoe.exception.LobbyExpiredException
import com.tw.tictactoe.exception.LobbyFullException
import com.tw.tictactoe.exception.LobbyWaitingException
import com.tw.tictactoe.model.LobbyStatus.*

enum class LobbyStatus {
    WAITING, READY, EXPIRED
}

class Lobby {
    private var status = WAITING
    private val players = ArrayList<String>(2)

    fun addPlayer(playerName: String) {
        if(status == READY) throw LobbyFullException()
        if(status == EXPIRED) throw LobbyExpiredException()

        players.add(playerName)

        if (players.size == 2) status = READY
    }

    fun status(): LobbyStatus {
        return status
    }

    fun startGame(): Game {
        if(players.size != 2) throw LobbyWaitingException()

        val p1 = Player(players[0], Symbol.X)
        val p2 = Player(players[1], Symbol.O)

        return Game(Players(p1, p2)).also { status = EXPIRED }
    }
}