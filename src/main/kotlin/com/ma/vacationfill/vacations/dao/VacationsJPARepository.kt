package com.ma.vacationfill.vacations.dao

import com.ma.vacationfill.vacations.Vacation
import com.ma.vacationfill.vacations.VacationsDatastore
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*


@Component
class VacationsJPADatastore(private val repo: VacationsJPARepository,
                            private val converter: VacationDomainDboConverter
) : VacationsDatastore {

    override fun getVacationById(vacationId: Long): Optional<Vacation> {
        return repo.findById(vacationId).map { converter.convertDbo(it) }
    }

    override fun getAllVacations(): List<Vacation> {
        return repo.findAll().map { converter.convertDbo(it) }
    }

    override fun saveVacation(vacation: Vacation) {
        val vacationDbo = converter.convertDomain(vacation)
        repo.save(vacationDbo)
    }
}

@Repository
interface VacationsJPARepository: JpaRepository<VacationDbo, Long>

