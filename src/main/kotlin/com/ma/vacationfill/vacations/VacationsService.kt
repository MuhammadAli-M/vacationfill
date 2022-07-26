package com.ma.vacationfill.vacations

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class VacationsService(private val datastore: VacationsDatastore) {

    fun getVacations(): List<Vacation> {
        return datastore.getAllVacations()
    }

    fun submitVacation(startDate: LocalDate, endDate: LocalDate, type: String) {
        val vacation = Vacation.new(startDate,endDate,type)
        datastore.saveVacation(vacation)
    }

    fun acceptVacation(vacationId: Long) { // TODO refactor to have request
        val vacation = getVacationOrThrow(vacationId)
        vacation.accepted()
        datastore.saveVacation(vacation)
    }

    fun rejectVacation(vacationId: Long) { // TODO refactor to have request
        val vacation = getVacationOrThrow(vacationId)
        vacation.rejected()
        datastore.saveVacation(vacation)
    }

    fun cancelVacation(vacationId: Long) { // TODO refactor to have request
        // TODO limit to the requester or the employee
        val vacation = getVacationOrThrow(vacationId)
        vacation.canceled()
        datastore.saveVacation(vacation)
    }

    private fun getVacationOrThrow(vacationId: Long): Vacation {
        val vacationOptional = datastore.getVacationById(vacationId)
        if (vacationOptional.isEmpty) {
            throw NoVacationFoundException(vacationId)
        }
        return vacationOptional.get()
    }
}
class NoVacationFoundException(private val vacationId: Long) : RuntimeException("No vacation found with Id: [$vacationId]")
