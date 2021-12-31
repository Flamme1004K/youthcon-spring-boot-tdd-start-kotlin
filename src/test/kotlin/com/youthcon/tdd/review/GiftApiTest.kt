package com.youthcon.tdd.review

import com.youthcon.tdd.infra.GiftApi
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GiftApiTest {
    private val giftApi : GiftApi = GiftApi()

    @Test
    fun `선물하기 보내기 통신 성공`() {
        assertThat(giftApi.send("010-1111-2222")).isTrue
    }
}