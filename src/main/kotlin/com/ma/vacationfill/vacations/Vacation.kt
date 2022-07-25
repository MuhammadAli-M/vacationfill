package com.ma.vacationfill.vacations

import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

class Vacation(
    val id: Long,
    val startDate: LocalDate,
    val endDate: LocalDate,
    state: State,
    val type: String,
    val requestedAt: LocalDate,
){
    var state = state
    private set
    fun accepted(){
        state = State.ACCEPTED
    }

    fun rejected(){
        state = State.REJECTED
    }

    fun canceled(){
        state = State.CANCELED
    }
}

enum class State(val string: String){
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED"),
    CANCELED("CANCELED"),
}