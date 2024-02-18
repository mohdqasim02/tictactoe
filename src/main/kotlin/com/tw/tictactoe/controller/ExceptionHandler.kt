package com.tw.tictactoe.controller

import com.tw.tictactoe.controller.dto.ErrorResponse
import com.tw.tictactoe.exception.OutOfRangeException
import com.tw.tictactoe.exception.PreOccupiedException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(PreOccupiedException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun preOccupiedErrorHandler(exception: PreOccupiedException): ErrorResponse {
        println(exception.message)
        return ErrorResponse(message = exception.message, position = exception.position)
    }

    @ExceptionHandler(OutOfRangeException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun outOfRangeErrorHandler(exception: OutOfRangeException): ErrorResponse {
        println(exception.message)
        return ErrorResponse(message = exception.message, position = exception.position)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun illegalMoveErrorHandler(exception: IllegalArgumentException): ErrorResponse {
        println(exception.message)
        return ErrorResponse(message = exception.message)
    }
}