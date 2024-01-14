package com.tw.tictactoe.controller

import com.tw.tictactoe.controller.dto.Move
import com.tw.tictactoe.controller.dto.PlayerNames
import com.tw.tictactoe.model.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/game")
class GameController {
    lateinit var game: Game

    @PostMapping(value = ["/start"], produces = ["application/json"])
    fun startGame(@RequestBody playerNames: PlayerNames){
        val player1 = Player(playerNames.firstPlayer, Symbol.X)
        val player2 = Player(playerNames.secondPlayer, Symbol.O)
        val players = Players(player1, player2)

        game = Game(players)
    }

    @PostMapping(value = ["/move"], produces = ["application/json"])
    fun makeMove(@RequestBody position: Move) {
        game.makeMove(position.position)
    }


    @GetMapping(value = ["/status"])
    fun getGameStatus(): GameStatus {
        return game.gameStatus()
    }
}