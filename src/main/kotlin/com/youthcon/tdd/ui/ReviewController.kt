package com.youthcon.tdd.ui

import com.youthcon.tdd.domain.Review
import com.youthcon.tdd.application.ReviewService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewController(private val reviewService: ReviewService) {

    @GetMapping("/reviews/{id}")
    fun getById(@PathVariable id: Long): Review = reviewService.getById(id)

    @PutMapping("/reviews/{id}")
    fun sendGift(@PathVariable id: Long): Review = reviewService.sendGift(id)
}
