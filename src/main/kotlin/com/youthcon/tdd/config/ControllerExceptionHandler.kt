package com.youthcon.tdd.config

import com.youthcon.tdd.dto.ErrorResponseDto
import com.youthcon.tdd.errors.DuplicateSendGiftException
import com.youthcon.tdd.errors.ReviewNotFoundException
import com.youthcon.tdd.errors.SendGiftInternalException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class ControllerExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateSendGiftException::class)
    fun handleDuplicateSendGiftException(ex: DuplicateSendGiftException): ErrorResponseDto = ErrorResponseDto(ex.message)


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReviewNotFoundException::class)
    fun handleReviewNotFoundException(ex: ReviewNotFoundException): ErrorResponseDto = ErrorResponseDto(ex.message)


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SendGiftInternalException::class)
    fun handleSendGiftInternalException(ex: SendGiftInternalException): ErrorResponseDto = ErrorResponseDto(ex.message)

}