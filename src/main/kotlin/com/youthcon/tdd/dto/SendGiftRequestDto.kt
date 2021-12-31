package com.youthcon.tdd.dto

data class SendGiftRequestDto(val phoneNumber: String, val amount: Int) {

    companion object {
        fun of(phoneNumber: String, amount: Int): SendGiftRequestDto = SendGiftRequestDto(phoneNumber, amount)
    }
}
