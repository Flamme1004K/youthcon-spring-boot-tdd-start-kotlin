package com.youthcon.tdd.review

import com.ninjasquad.springmockk.MockkBean
import com.youthcon.tdd.application.ReviewService
import com.youthcon.tdd.domain.Review
import com.youthcon.tdd.errors.ReviewNotFoundException
import com.youthcon.tdd.ui.ReviewController
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ReviewController::class)
class ReviewControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var reviewService: ReviewService

    @Test
    fun `후기 조회 성공`() {
        // given
        every { reviewService.getById(ID) }.returns(Review(ID, CONTENT, PHONE_NUMBER))

        // when
        val perform = mockMvc.perform(get("/reviews/$ID"))

        // then
        perform.andExpect(status().isOk)
            .andExpect(jsonPath("id").value(ID))
            .andExpect(jsonPath("content").value(CONTENT))
            .andExpect(jsonPath("phoneNumber").value(PHONE_NUMBER))
    }

    @Test
    fun `후기 조회 실패`() {
        // given
        every { reviewService.getById(1000L) }.throws(ReviewNotFoundException("no review: ${1000L}"))

        // when
        val perform = mockMvc.perform(get("/reviews/${1000L}"))

        // then
        perform.andExpect(status().isNotFound)
    }

    @Test
    fun `선물하기`() {
        // given
        every { reviewService.sendGift(ID) }.returns(Review(ID, CONTENT, PHONE_NUMBER, true))

        // when
        val perform = mockMvc.perform(put("/reviews/$ID"))

        // then
        perform.andExpect(status().isOk)
            .andExpect(jsonPath("sent").value(true))
    }

    companion object {
        private const val ID: Long = 1L
        private const val CONTENT: String = "재밌어요"
        private const val PHONE_NUMBER: String = "010-1111-2222"
    }
}