package com.tw.tictactoe.exception

class PreOccupiedException(
    private val position: Int,
    override val message: String? = "$position is already occupied"
) : Throwable()
