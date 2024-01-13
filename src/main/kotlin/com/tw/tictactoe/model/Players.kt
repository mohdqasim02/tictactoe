package com.tw.tictactoe.model

class Players(p1: Player, p2: Player) {
    private var players = listOf(p1, p2)

    val currentPlayer
        get() = players[0]

    fun recordMove(position: Int) {
        players[0].recordMove(position)
    }

    fun changeTurn() {
        players = this.players.reversed()
    }

    fun movesMade(): Map<Int, Symbol> {
        val movesMade = mutableMapOf<Int, Symbol>()
        movesMade.putAll(players[0].movesMade())
        movesMade.putAll(players[1].movesMade())

        return movesMade
    }
}
