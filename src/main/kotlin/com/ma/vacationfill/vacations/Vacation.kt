package com.ma.vacationfill.vacations

import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

class Vacation(
    val id: Long,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val state: String,
    val type: String,
    val requestedAt: LocalDate,
)