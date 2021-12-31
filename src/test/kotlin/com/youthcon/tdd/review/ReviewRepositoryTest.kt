package com.youthcon.tdd.review

import com.youthcon.tdd.infra.ReviewRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    private lateinit var reviewRepository: ReviewRepository

    @Test
    fun `후기 조회 성공`() {
        // given

        // when
        val review = reviewRepository.findById(1L).orElseThrow { RuntimeException() }

        // then
        assertAll(
            { assertThat(review.id).isEqualTo(1L) },
            { assertThat(review.content).isEqualTo("재밌어요") },
            { assertThat(review.phoneNumber).isEqualTo("010-1111-2222") }
        )
    }

}