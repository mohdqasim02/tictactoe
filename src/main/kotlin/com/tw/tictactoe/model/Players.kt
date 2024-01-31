package com.tw.tictactoe.model

class Players(p1: Player, p2: Player) {
    val playerList = listOf(p1, p2)
    var nextPlayer = p2
    var currentPlayer = p1

    fun recordMove(position: Int) {
        currentPlayer.recordMove(position)
    }

    fun changeTurn() {
        val temp = currentPlayer

        currentPlayer = nextPlayer
        nextPlayer = temp
    }

    fun movesMade(): Map<Int, Symbol> {
        val movesMade = mutableMapOf<Int, Symbol>()
        movesMade.putAll(playerList[0].movesMade())
        movesMade.putAll(playerList[1].movesMade())

        return movesMade
    }
}
