package com.ma.vacationfill.vacations

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class VacationsController(private val vacationsService: VacationsService) {

    @GetMapping(path = ["api/v1/vacations"])
    fun getVacations(): List<Vacation> = vacationsService.getVacations()

    @PostMapping(path = ["api/v1/vacations"])
    fun submitVacation(@RequestBody requestBodyDto: VacationSubmissionRequestBodyDto) {
        // Add validations
        if (requestBodyDto.startDate < LocalDate.now() || requestBodyDto.endDate < LocalDate.now()){
            throw RuntimeException("Vacation start and end date should be in the future")
        }

        if (requestBodyDto.startDate > requestBodyDto.endDate){
            throw RuntimeException("Vacation end date should be after its start date")
        }
        vacationsService.submitVacation(requestBodyDto.startDate, requestBodyDto.endDate, requestBodyDto.type)
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


data class VacationSubmissionRequestBodyDto(
    val startDate: LocalDate,
    val endDate: LocalDate,
    val type: String,
)