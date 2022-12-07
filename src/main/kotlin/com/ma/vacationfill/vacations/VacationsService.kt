package com.ma.vacationfill.vacations

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class VacationsService(private val repo: VacationsRepo) {

    fun submitVacation(startDate: LocalDate, endDate: LocalDate, type: String): Long {

        assertStartAndEndDateInFuture(startDate, endDate)
        assertStartBeforeEndDate(startDate, endDate)

        val vacationType = getVacationTypeOrThrow(type)
        var vacation = Vacation.new(startDate,endDate,vacationType)
        vacation = repo.saveVacation(vacation)
        return vacation.id!!
    }

    fun acceptVacation(vacationId: Long) { // TODO refactor to have request
        val vacation = getVacationOrThrow(vacationId)
        vacation.accepted()
        repo.saveVacation(vacation)
    }

    fun rejectVacation(vacationId: Long) { // TODO refactor to have request
        val vacation = getVacationOrThrow(vacationId)
        vacation.rejected()
        repo.saveVacation(vacation)
    }

    fun cancelVacation(vacationId: Long) { // TODO refactor to have request
        // TODO limit to the requester or the employee
        val vacation = getVacationOrThrow(vacationId)
        vacation.canceled()
        repo.saveVacation(vacation)
    }

    fun getVacations(): List<Vacation> {
        return repo.getAllVacations()
    }

    fun getVacation(vacationId: Long): Vacation {
        return this.getVacationOrThrow(vacationId)
    }

    private fun getVacationOrThrow(vacationId: Long): Vacation {
        val vacationOptional = repo.getVacationById(vacationId)
        if (vacationOptional.isEmpty) {
            throw NoVacationFoundException(vacationId)
        }
        return vacationOptional.get()
    }

    private fun assertStartBeforeEndDate(startDate: LocalDate, endDate: LocalDate) {
        if (startDate > endDate) {
            throw VacationStartDateAfterEndDateException(startDate, endDate)
        }
    }

    private fun assertStartAndEndDateInFuture(startDate: LocalDate, endDate: LocalDate) {
        val currentDate = LocalDate.now()
        if (startDate < currentDate || endDate < currentDate) {
            throw VacationStartOrEndDateInPastException(startDate, endDate, currentDate)
        }
    }

    private fun getVacationTypeOrThrow(type: String): VacationType {
        if (!VacationType.values().map { it.string }.contains(type)) {
            throw VacationTypeUnsupportedException(type)
        }
        return VacationType.valueOf(type)
    }
}

class NoVacationFoundException(private val vacationId: Long) : RuntimeException("No vacation found with Id: [$vacationId]")

class VacationStartDateAfterEndDateException(startDate: LocalDate, endDate: LocalDate)
    : RuntimeException(""" Vacation end date should be after its start date, start: [$startDate], end: [$endDate]""")

class VacationStartOrEndDateInPastException(startDate: LocalDate, endDate: LocalDate, currentDate: LocalDate)
    : RuntimeException (""" Vacation start and end date should not be in the past. start: [$startDate], end: 
        [$endDate], current: [$currentDate]
    """.trimIndent())

class VacationTypeUnsupportedException(private val type: String) : RuntimeException("Vacation type is not supported: " +
        "[$type]")