package com.ma.vacationfill.vacations

import org.springframework.web.bind.annotation.GetMapping
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

    @PostMapping(path = ["api/v1/vacations/accept"])
    fun acceptVacation(@RequestBody bodyDto: VacationAcceptanceRequestBodyDto) {
        vacationsService.acceptVacation(bodyDto.vacationId)
    }

    @PostMapping(path = ["api/v1/vacations/reject"])
    fun rejectVacation(@RequestBody bodyDto: VacationAcceptanceRequestBodyDto) {
        vacationsService.rejectVacation(bodyDto.vacationId)
    }
}


data class VacationAcceptanceRequestBodyDto(
    val vacationId: Long
)