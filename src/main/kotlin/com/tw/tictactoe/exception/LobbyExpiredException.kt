package com.tw.tictactoe.exception
class LobbyExpiredException(override val message: String? = "Lobby is expired"): Throwable()