package com.tw.tictactoe.exception

class GameOverException(override val message: String? = "Game is already over"): Throwable()