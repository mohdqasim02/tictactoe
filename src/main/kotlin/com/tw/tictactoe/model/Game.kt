package com.tw.tictactoe.model

import com.tw.tictactoe.exception.GameOverException
import com.tw.tictactoe.exception.LobbyFullException
import com.tw.tictactoe.exception.OutOfRangeException
import com.tw.tictactoe.exception.PreOccupiedException

data class GameStatus(
    val moves: Map<Int, Symbol>,
    val currentPlayer: Player,
    val isGameOver: Boolean,
    val winner: String?
)

class Game(private val players: Players) {
    private var isGameOver: Boolean = false
    private var winner: String? = null

    private fun isInRange(position: Int): Boolean = position in 1..9
    private fun hasAllMovesMade(): Boolean = players.movesMade().size == 9
    private fun isPositionOccupied(position: Int): Boolean = players.movesMade().containsKey(position)

    fun makeMove(position: Int) {
        if(isGameOver) throw GameOverException()
        if(!isInRange(position)) throw OutOfRangeException(position)
        if(isPositionOccupied(position)) throw PreOccupiedException(position)

        players.recordMove(position)

        if(players.currentPlayer.hasWon()) {
            isGameOver = true
            winner = players.currentPlayer.name
            return
        }

        if(this.hasAllMovesMade()) {
            isGameOver = true
            return
        }

        players.changeTurn()
    }

    fun gameStatus() = GameStatus(players.movesMade(), players.currentPlayer, isGameOver, winner)
}