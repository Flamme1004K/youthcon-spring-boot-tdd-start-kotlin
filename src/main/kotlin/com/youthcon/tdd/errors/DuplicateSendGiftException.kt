package com.youthcon.tdd.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class DuplicateSendGiftException(override val message: String) : RuntimeException()
