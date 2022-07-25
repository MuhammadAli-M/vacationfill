package com.ma.vacationfill.vacations

import org.springframework.stereotype.Service

@Service
class VacationsService(private val datastore: VacationsDatastore) {

    fun getVacations(): List<Vacation> {
        return datastore.getAllVacations()
    }

    fun submitVacation(vacation: Vacation) {
        datastore.saveVacation(vacation)
    }

    fun acceptVacation(vacationId: Long) { // TODO refactor to have request
        val vacation = datastore.getVacationById(vacationId)
        if (vacation.isEmpty){
            throw Exception("No vacation found with Id: [$vacationId]")
        }

//        repository.save(vacation)
    }

    fun rejectVacation(vacationId: Long) { // TODO refactor to have request
//        repository.save(vacation)
    }

}