package com.youthcon.tdd.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SendGiftResponseDto(
    @JsonProperty("id") val id: String,
    @JsonProperty("amount") val amount: Int
)
