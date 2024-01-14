package com.tw.tictactoe.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.tw.tictactoe.controller.dto.Move
import com.tw.tictactoe.controller.dto.PlayerNames
import com.tw.tictactoe.model.GameStatus
import com.tw.tictactoe.model.Player
import com.tw.tictactoe.model.Symbol
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest{

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun `should start a game when two players are provided`() {
        val players = PlayerNames("Mohd", "Qasim")

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/game/start")
                .content(objectMapper.writeValueAsString(players))
                .header("content-type", "application/json")
        ).andExpectAll(
            MockMvcResultMatchers.status().is2xxSuccessful(),
        )
    }

    @Test
    fun `should make move for current player`() {
        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/game/move")
                .content(objectMapper.writeValueAsString(Move(4)))
                .header("content-type", "application/json")
        ).andExpectAll(
            MockMvcResultMatchers.status().is2xxSuccessful()
        )
    }

    @Test
    fun `should return game status`() {
        val players = PlayerNames("Mohd", "Qasim")
        val gameStatus = GameStatus(mapOf(), Player("Mohd", Symbol.X), false, null)

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/game/start")
                .content(objectMapper.writeValueAsString(players))
                .header("content-type", "application/json")
        )

        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/game/status")
        ).andExpectAll(
            MockMvcResultMatchers.status().is2xxSuccessful(),
            MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(gameStatus)),
            MockMvcResultMatchers.header().string("content-type", "application/json")
        )
    }
}