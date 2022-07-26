package com.ma.vacationfill.vacations

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class VacationsController(private val vacationsService: VacationsService) {

    @GetMapping(path = ["api/v1/vacations"])
    fun getVacations(): List<Vacation> = vacationsService.getVacations()

    @PostMapping(path = ["api/v1/vacations"])
    fun addVacation(@RequestBody vacation: Vacation) {
        // Add validations
        vacationsService.submitVacation(vacation)
    }

    @PostMapping(path = ["api/v1/vacations/{vacationId}/accept"])
    fun acceptVacation(@PathVariable vacationId: Long) {
        vacationsService.acceptVacation(vacationId)
    }

    @PostMapping(path = ["api/v1/vacations/{vacationId}/reject"])
    fun rejectVacation(@PathVariable vacationId: Long) {
        vacationsService.rejectVacation(vacationId)
    }

    @PostMapping(path = ["api/v1/vacations/{vacationId}/cancel"])
    fun cancelVacation(@PathVariable vacationId: Long) {
        vacationsService.cancelVacation(vacationId)
    }
}


data class VacationAcceptanceRequestBodyDto(
    val vacationId: Long
)