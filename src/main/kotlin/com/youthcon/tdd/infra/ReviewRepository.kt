package com.youthcon.tdd.infra

import com.youthcon.tdd.domain.Review
import com.youthcon.tdd.errors.ReviewNotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

fun ReviewRepository.getByReviewId(id: Long): Review = findByIdOrNull(id) ?: throw ReviewNotFoundException("no review: $id")

interface ReviewRepository : JpaRepository<Review, Long>
