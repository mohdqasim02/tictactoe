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
    val players = ArrayList<Player>(2)

    fun addPlayer(player: Player) {
        if(status == READY) throw LobbyFullException()
        if(status == EXPIRED) throw LobbyExpiredException()

        players.add(player)

        if (players.size == 2) status = READY
    }

    fun status(): LobbyStatus {
        return status
    }

    fun startGame(): Game {
        if(players.size != 2) throw LobbyWaitingException()

        val p1 = players[0]
        val p2 = players[1]

        return Game(Players(p1, p2)).also { status = EXPIRED }
    }
}