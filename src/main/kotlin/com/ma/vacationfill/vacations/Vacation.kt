package com.ma.vacationfill.vacations

import java.time.LocalDate

class Vacation(
    val id: Long? = null,
    val startDate: LocalDate,
    val endDate: LocalDate,
    state: VacationState,
    val type: String,
    val requestedAt: LocalDate,
){
    var state = state
    private set

    companion object{
        fun new(
            startDate: LocalDate,
            endDate: LocalDate,
            type: String,
        ) = Vacation(
            startDate = startDate,
            endDate = endDate,
            type = type,
            state = VacationState.PENDING,
            requestedAt = LocalDate.now()
        )
    }
    fun accepted(){
        state = VacationState.ACCEPTED
    }

    fun rejected(){
        state = VacationState.REJECTED
    }

    fun canceled(){
        state = VacationState.CANCELED
    }
}

enum class VacationState(val string: String){
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED"),
    CANCELED("CANCELED"),
}