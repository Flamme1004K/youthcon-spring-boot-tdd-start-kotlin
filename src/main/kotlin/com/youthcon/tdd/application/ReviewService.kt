package com.youthcon.tdd.application

import com.youthcon.tdd.domain.Review
import com.youthcon.tdd.errors.DuplicateSendGiftException
import com.youthcon.tdd.errors.SendGiftInternalException
import com.youthcon.tdd.infra.GiftApi
import com.youthcon.tdd.infra.ReviewRepository
import com.youthcon.tdd.infra.getByReviewId
import org.springframework.stereotype.Service

@Service
class ReviewService(private val reviewRepository: ReviewRepository, private val giftApi: GiftApi) {

    fun getById(id: Long): Review = reviewRepository.getByReviewId(id)

    fun sendGift(id: Long): Review {
        val review = reviewRepository.getByReviewId(id)

        if (review.sent) {
            throw DuplicateSendGiftException("duplicate review id :$id")
        }

        if (!giftApi.send(review.phoneNumber)) {
            throw SendGiftInternalException("internal exception")
        }

        review.makeTrue()

        return review
    }

}
