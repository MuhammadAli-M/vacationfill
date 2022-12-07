package com.ma.vacationfill.vacations.infrastructure.dao

import com.ma.vacationfill.vacations.Vacation
import com.ma.vacationfill.vacations.VacationsRepo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*


@Component
class VacationsJPARepo(private val repo: VacationsJPARepository,
                       private val converter: VacationDomainDboConverter
) : VacationsRepo {

    override fun getVacationById(vacationId: Long): Optional<Vacation> {
        return repo.findById(vacationId).map { converter.convertDbo(it) }
    }

    override fun getAllVacations(): List<Vacation> {
        return repo.findAll().map { converter.convertDbo(it) }
    }

    override fun saveVacation(vacation: Vacation): Vacation { // insert and save together
        val resultDbo: VacationDbo
        if (vacation.id != null){
            val existingVacation = repo.findById(vacation.id).map { converter.convertDbo(it) }
            val vacationDbo = converter.convertDomain(vacation)
            if (existingVacation.isPresent){
                resultDbo = repo.save(vacationDbo.copy(id = existingVacation.get().id))
            }else{
                resultDbo = repo.save(vacationDbo)
            }
        }else{
            resultDbo = repo.save(converter.convertDomain(vacation))
        }
        return converter.convertDbo(resultDbo)
    }
}

@Repository
interface VacationsJPARepository: JpaRepository<VacationDbo, Long>

