package com.tw.tictactoe.exception

class OutOfRangeException(
    val position: Int,
    override val message: String? = "$position is not in the range of 1 to 9"
) : Throwable()
