package com.ma.vacationfill.vacations

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate


@RestController
class VacationsController(private val vacationsService: VacationsService) {

    @GetMapping(path = ["api/v1/vacations"])
    fun getVacations(): List<Vacation> {
        return vacationsService.getVacations()
    }

    @GetMapping(path = ["api/v1/vacations/{vacationId}"])
    fun getVacations(@PathVariable vacationId: Long): Vacation {
        return vacationsService.getVacation(vacationId)
    }

    @PostMapping(path = ["api/v1/vacations"])
    fun submitVacation(@RequestBody requestBodyDto: VacationSubmissionRequestBodyDto): ResponseEntity<VacationSubmissionResponseDto> {

        try {
            val id =
                vacationsService.submitVacation(requestBodyDto.startDate, requestBodyDto.endDate, requestBodyDto.type)

            val responseBody = VacationSubmissionResponseDto(id.toString())
            return ResponseEntity(responseBody, HttpStatus.CREATED)

        } catch (ex: RuntimeException) { // TODO clean this part
            return this.mapVacationSubmissionError(ex)
        }
    }

    private fun mapVacationSubmissionError(ex: RuntimeException): ResponseEntity<VacationSubmissionResponseDto> {
        val badRequestExceptions = listOf(
            VacationTypeUnsupportedException::class,
            VacationStartOrEndDateInPastException::class,
            VacationStartDateAfterEndDateException::class
        )
        if (ex::class in badRequestExceptions) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        } else {
            throw ex
        }
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

@ControllerAdvice
class NoVacationFoundExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun execute(ex: NoVacationFoundException): String {
        return ex.message ?: ""
    }
}

// Requests DTOs
data class VacationSubmissionRequestBodyDto(
    val startDate: LocalDate,
    val endDate: LocalDate,
    val type: String,
)

data class VacationSubmissionResponseDto (val vacationId: String)