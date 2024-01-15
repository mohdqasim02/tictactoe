package com.tw.tictactoe.exception
class LobbyWaitingException(override val message: String? = "Not enough players in the lobby"): Throwable()