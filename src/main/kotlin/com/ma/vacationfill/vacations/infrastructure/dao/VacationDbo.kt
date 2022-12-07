package com.ma.vacationfill.vacations.infrastructure.dao

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "vacations")
data class VacationDbo(
    @Id
    @SequenceGenerator(
        name = "vacation_sequence",
        sequenceName = "vacation_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "vacation_sequence"
    )
    val id: Long? = null,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val state: String,
    val type: String,
    val requestedAt: LocalDate,
)