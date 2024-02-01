package com.tw.tictactoe.controller

import com.tw.tictactoe.controller.dto.Move
import com.tw.tictactoe.controller.dto.PlayerName
import com.tw.tictactoe.model.GameStatus
import com.tw.tictactoe.model.LobbyStatus
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class GameApiController(val gameController: GameController) {
    @PostMapping(value = ["/lobby/add"])
    fun registerPlayer(@RequestBody player: PlayerName): ResponseEntity<UUID> {
        val lobbyId = gameController.addPlayer(player.name)

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, "lobbyId=$lobbyId")
            .body(lobbyId)
    }

    @GetMapping(value = ["/lobby/status"])
    fun getLobbyStatus(@CookieValue lobbyId: UUID): ResponseEntity<LobbyStatus> {
        val lobbyStatus = gameController.getLobbyStatus(lobbyId)
        return ResponseEntity.ok().body(lobbyStatus)
    }

    @PostMapping(value = ["/lobby/start"])
    fun startGame(@CookieValue lobbyId: UUID): ResponseEntity<Nothing> {
        gameController.startGame(lobbyId)

        return ResponseEntity
            .ok()
            .header(HttpHeaders.COOKIE, "lobbyId=null;maxAge=0")
            .header(HttpHeaders.SET_COOKIE, "gameId=$lobbyId;path=/game")
            .body(null)
    }

    @PostMapping(value = ["/game/move"])
    fun makeMove(@CookieValue gameId: UUID, @RequestBody move: Move) {
        gameController.makeMove(gameId, move.position)
    }

    @GetMapping(value = ["/game/status"], produces = ["application/json"])
    fun getGameStatus(@CookieValue gameId: UUID): GameStatus {
        return gameController.getGameStatus(gameId)
    }
}