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

    override fun saveVacation(vacation: Vacation) { // insert and save together
        if (vacation.id != null){
            val existingVacation = repo.findById(vacation.id).map { converter.convertDbo(it) }
            val vacationDbo = converter.convertDomain(vacation)
            if (existingVacation.isPresent){
                repo.save(vacationDbo.copy(id = existingVacation.get().id))
            }else{
                repo.save(vacationDbo)
            }
        }else{
            repo.save(converter.convertDomain(vacation))
        }
    }
}

@Repository
interface VacationsJPARepository: JpaRepository<VacationDbo, Long>

