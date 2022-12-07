package com.ma.vacationfill.vacations

import java.util.*

interface VacationsRepo {
    fun getVacationById(vacationId: Long): Optional<Vacation>
    fun getAllVacations(): List<Vacation>
    fun saveVacation(vacation: Vacation): Vacation
}
