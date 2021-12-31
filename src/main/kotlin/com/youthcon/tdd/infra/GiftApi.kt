package com.youthcon.tdd.infra

import com.youthcon.tdd.dto.SendGiftRequestDto
import com.youthcon.tdd.dto.SendGiftResponseDto
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class GiftApi {

    private val restTemplate : RestTemplate = RestTemplate()

    fun send(phoneNumber: String): Boolean {
        val response = restTemplate.postForEntity(URL, SendGiftRequestDto.of(phoneNumber, AMOUNT), SendGiftResponseDto::class.java)
        return response.statusCode.is2xxSuccessful
    }

    companion object {
        private const val URL: String = "http://youthcon.seok2.dev/apis/v1/send"
        private const val AMOUNT: Int = 1000
    }
}