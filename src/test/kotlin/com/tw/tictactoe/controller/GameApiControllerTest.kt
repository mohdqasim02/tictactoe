package com.tw.tictactoe.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import com.tw.tictactoe.controller.dto.Move
import com.tw.tictactoe.controller.dto.PlayerName
import com.tw.tictactoe.model.GameStatus
import com.tw.tictactoe.model.LobbyStatus.WAITING
import com.tw.tictactoe.model.Player
import com.tw.tictactoe.model.Symbol.O
import com.tw.tictactoe.model.Symbol.X
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import jakarta.servlet.http.Cookie
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class GameApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockkBean
    lateinit var gameController: GameController

    private val lobbyId = UUID.randomUUID()

    @Test
    fun `should return lobbyId when a player registers to the game`() {
        val player = PlayerName("Mohd")

        every { gameController.addPlayer(any()) } returns Pair(lobbyId, O)

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/lobby/add")
                .content(objectMapper.writeValueAsString(player))
                .header("content-type", "application/json")
        ).andExpectAll(
            MockMvcResultMatchers.status().is2xxSuccessful(),
            MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(lobbyId)),
            MockMvcResultMatchers.cookie().value("lobbyId", lobbyId.toString())
        )
    }

    @Test
    fun `should return lobbyStatus based on lobbyId present in the cookie`() {
        val cookie = Cookie("lobbyId", lobbyId.toString())

        every { gameController.getLobbyStatus(any()) } returns WAITING

        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/lobby/status")
                .cookie(cookie)
        ).andExpectAll(
            MockMvcResultMatchers.status().is2xxSuccessful(),
            MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(WAITING)),
        )
    }

    @Test
    fun `should start game based on lobbyId present in the cookie`() {
        every { gameController.startGame(any()) } just(Runs)

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/lobby/start")
                .cookie(Cookie("lobbyId", lobbyId.toString()))
                .cookie(Cookie("symbol", O.name))
        ).andExpectAll(
            MockMvcResultMatchers.status().is2xxSuccessful(),
            MockMvcResultMatchers.cookie().value("gameId", lobbyId.toString()),
            MockMvcResultMatchers.cookie().doesNotExist("lobbyId")
        )
    }

    @Test
    fun `should make move for the current player based on gameId present in the cookie`() {
        every { gameController.makeMove(lobbyId, any(), O) } just(Runs)

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/game/move")
                .cookie(Cookie("gameId", lobbyId.toString()))
                .cookie(Cookie("symbol", O.name))
                .header("content-type", "application/json")
                .content(objectMapper.writeValueAsString(Move(6)))
        ).andExpectAll(
            MockMvcResultMatchers.status().is2xxSuccessful(),
        )
    }

    @Test
    fun `should return game status based on gameId present in the cookie`() {
        val gameStatus = GameStatus(listOf(), mapOf(), Player("Mohd", O), Player("Qasim", X), false, null)
        val cookie = Cookie("gameId", lobbyId.toString())

        every { gameController.getGameStatus( any()) } returns gameStatus

        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/game/status")
                .cookie(cookie)
        ).andExpectAll(
            MockMvcResultMatchers.status().is2xxSuccessful(),
            MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(gameStatus)),
        )
    }
}