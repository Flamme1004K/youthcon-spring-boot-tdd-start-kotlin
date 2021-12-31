package com.youthcon.tdd.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Review(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val content: String,
    val phoneNumber: String,
    var sent: Boolean = false
) {
    fun makeTrue() {
        sent = true
    }
}
