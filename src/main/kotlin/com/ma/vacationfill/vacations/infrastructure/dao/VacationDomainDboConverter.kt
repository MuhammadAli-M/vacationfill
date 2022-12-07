package com.ma.vacationfill.vacations.infrastructure.dao

import com.ma.vacationfill.vacations.VacationState
import com.ma.vacationfill.vacations.Vacation
import com.ma.vacationfill.vacations.VacationType
import org.springframework.stereotype.Component

@Component
class VacationDomainDboConverter{
    fun convertDomain(vacation: Vacation) = VacationDbo(
        id = vacation.id,
        startDate = vacation.startDate,
        endDate = vacation.endDate,
        state = vacation.state.toString(),
        type = vacation.type.toString(),
        requestedAt = vacation.requestedAt
    )

    fun convertDbo(vacationDbo: VacationDbo) = Vacation(
        id = vacationDbo.id!!,
        startDate = vacationDbo.startDate,
        endDate = vacationDbo.endDate,
        state = VacationState.valueOf(vacationDbo.state),
        type =VacationType.valueOf(vacationDbo.type),
        requestedAt = vacationDbo.requestedAt
    )
}