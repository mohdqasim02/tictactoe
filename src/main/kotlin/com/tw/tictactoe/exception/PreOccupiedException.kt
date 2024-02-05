package com.tw.tictactoe.exception

class PreOccupiedException(
    val position: Int,
    override val message: String? = "$position is already occupied"
) : Throwable()
