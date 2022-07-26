package com.ma.vacationfill.vacations.dao

import com.ma.vacationfill.vacations.VacationState
import com.ma.vacationfill.vacations.Vacation
import org.springframework.stereotype.Component

@Component
class VacationDomainDboConverter{
    fun convertDomain(vacation: Vacation) = VacationDbo(
        id = vacation.id,
        startDate = vacation.startDate,
        endDate = vacation.endDate,
        state = vacation.state.toString(),
        type = vacation.type,
        requestedAt = vacation.requestedAt
    )

    fun convertDbo(vacationDbo: VacationDbo) = Vacation(
        id = vacationDbo.id!!,
        startDate = vacationDbo.startDate,
        endDate = vacationDbo.endDate,
        state = VacationState.valueOf(vacationDbo.state),
        type = vacationDbo.type,
        requestedAt = vacationDbo.requestedAt
    )
}