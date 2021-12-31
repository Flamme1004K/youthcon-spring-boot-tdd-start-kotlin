package com.youthcon.tdd.review

import com.youthcon.tdd.application.ReviewService
import com.youthcon.tdd.domain.Review
import com.youthcon.tdd.errors.DuplicateSendGiftException
import com.youthcon.tdd.errors.ReviewNotFoundException
import com.youthcon.tdd.errors.SendGiftInternalException
import com.youthcon.tdd.infra.GiftApi
import com.youthcon.tdd.infra.ReviewRepository
import com.youthcon.tdd.infra.getByReviewId
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class ReviewServiceTest {

    private val giftApi: GiftApi = mockk()
    private val reviewRepository: ReviewRepository = mockk()
    private val reviewService = ReviewService(reviewRepository, giftApi)


    @Test
    fun `후기 조회 성공`() {
        // given
        every { reviewRepository.getByReviewId(ID) } returns Review(ID, CONTENT, PHONE_NUMBER)

        // when
        val review = reviewService.getById(ID)

        // then
        assertAll(
            { assertThat(review.id).isEqualTo(ID) },
            { assertThat(review.content).isEqualTo(CONTENT) },
            { assertThat(review.phoneNumber).isEqualTo(PHONE_NUMBER) }
        )
    }

    @Test
    fun `후기 조회 실패`() {
        // given
        every { reviewRepository.getByReviewId(1000L) } throws ReviewNotFoundException("no review: $ID")

        // when
        assertThrows<ReviewNotFoundException> { reviewService.getById(1000L) }
    }

    @Test
    fun `선물하기 성공`() {
        // given
        every { reviewRepository.getByReviewId(ID) } returns Review(ID, CONTENT, PHONE_NUMBER)
        every { giftApi.send(PHONE_NUMBER) } returns true
        every { reviewRepository.save(any()) } returns Review(ID, CONTENT, PHONE_NUMBER, true)

        // when
        val review = reviewService.sendGift(ID)

        // then
        assertAll(
            { assertThat(review.id).isEqualTo(ID) },
            { assertThat(review.sent).isEqualTo(true) }
        )
    }

    @Test
    fun `선물하기 중복 지급 실패`() {
        // given
        every { reviewRepository.getByReviewId(ID) } returns Review(ID, CONTENT, PHONE_NUMBER, true)

        // then
        assertThrows<DuplicateSendGiftException> {
            reviewService.sendGift(ID)
        }
    }

    @Test
    fun `선물하기 외부 요청 실패`() {
        // given
        every { reviewRepository.getByReviewId(ID) } returns Review(ID, CONTENT, PHONE_NUMBER)
        every { giftApi.send(PHONE_NUMBER) } returns false

        // then
        assertThrows<SendGiftInternalException> {
            reviewService.sendGift(ID)
        }
    }


    companion object {
        private const val ID: Long = 1L
        private const val CONTENT: String = "재밌어요"
        private const val PHONE_NUMBER: String = "010-1111-2222"
    }
}
