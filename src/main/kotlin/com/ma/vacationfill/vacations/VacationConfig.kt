package com.ma.vacationfill.vacations

import com.ma.vacationfill.vacations.dao.VacationsJPADatastore
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDate

@Configuration
class VacationConfig {
    @Bean
    fun commandLineRunner(repository: VacationsJPADatastore): CommandLineRunner{
        return CommandLineRunner {
            val vacations = listOf(
                Vacation(
                    id = 1,
                    startDate = LocalDate.of(2022, 5, 22),
                    endDate = LocalDate.of(2022, 5, 24),
                    state = VacationState.valueOf("PENDING"),
                    type = "ANNUAL",
                    requestedAt = LocalDate.now(),
                ),
                Vacation(
                    id = 2,
                    startDate = LocalDate.of(2022, 5, 22),
                    endDate = LocalDate.of(2022, 5, 24),
                    state = VacationState.valueOf("PENDING"),
                    type = "ANNUAL",
                    requestedAt = LocalDate.now(),
                )
            )
            vacations.map { repository.saveVacation(it) } // TODO: provide saveAll functionality
        }
    }
}