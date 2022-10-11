package org.brightmindenrichment.street_care.ui.visit.visit_forms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.brightmindenrichment.street_care.ui.visit.data.VisitLog
import org.brightmindenrichment.street_care.ui.visit.repository.VisitLogRepository
import org.brightmindenrichment.street_care.ui.visit.repository.VisitLogRepositoryImp
import java.util.*

class VisitViewModel : ViewModel() {
    private val repository: VisitLogRepository = VisitLogRepositoryImp()
    private var _location = MutableLiveData<String>()
    val location: LiveData<String> get() = _location
    private var _date = MutableLiveData<Date>(Calendar.getInstance().time)
    val date: LiveData<Date> get() = _date
    private var _hours = MutableLiveData<Long>()
    val hours: LiveData<Long> = _hours
    private var _visitAgain = MutableLiveData<String>()
    val visitAgain: LiveData<String> get() = _visitAgain
    private var _peopleCount = MutableLiveData<Long>()
    val peopleCount: LiveData<Long> get() = _peopleCount
    private val _experience = MutableLiveData<String>()
    val experience get() = _experience
    private val _comments = MutableLiveData<String>()
    val comments:LiveData<String> get() = _comments

    private val _visitLog1 = MutableLiveData<List<VisitLog>>()
    val visitLog1 get() = _visitLog1


    private val visitLog: VisitLog = VisitLog()


    init {
        resetVisitLogPage()
    }

    fun setLocation(location: String) {
        _location.value = location
        visitLog.location = location
    }

    fun setDate(date: Date) {
        _date.value = date
        visitLog.date = date
    }

    fun setHours(hours: Long) {
        _hours.value = hours
        visitLog.hours = hours
    }

    fun setVisitAgain(visit: String) {
        _visitAgain.value = visit
        visitLog.visitAgain = visit
    }

    fun setPeopleCount(noOfPeople: Long) {
        _peopleCount.value = noOfPeople
        visitLog.peopleCount = noOfPeople
    }

    fun setExperience(experience: String) {
        _experience.value = experience
        visitLog.experience = experience
    }

    fun setComments(comments: String) {
        _comments.value = comments
        visitLog.comments = comments
    }

    fun submitVisitLog() {
        println("viewmodel submit visit log called----------------")
        repository.addVisitLog(visitLog)
    }

//    fun resetVisitLogPage1() {
//        //val myCalendar: Calendar = Calendar.getInstance()
//        println("resetVisitPage1 called========================")
//        _location.value = ""
//        _hours.value = 0L
//        _date.value = date.value
//        _peopleCount.value = 0L
//    }

    fun resetVisitLogPage() {
        //val myCalendar: Calendar = Calendar.getInstance()
        println("resetVisitPage called========================")
        _location.value = ""
        _hours.value = 0L
        _visitAgain.value = "Yes"
        _peopleCount.value = 0L
        _experience.value = ""
        _comments.value = ""
    }


    fun validateLocation(location: String): Boolean {
        // Most of the cases user will fill the location, so it won't be empty
        return !(location.isNullOrEmpty())
    }

    fun validateDate(visitedDate: Date): Boolean {
        val currentDate: Date = Calendar.getInstance().time
        return visitedDate <= currentDate
    }


    fun increment(): Long {
        return _peopleCount.value!!.inc()
    }

    fun decrement(): Long {
        return if (_peopleCount.value!! > 0) {
            _peopleCount.value!!.dec()

        } else
            0
    }


//    companion object {
//
//        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                modelClass: Class<T>,
//
//            ): T {
//                // Get the Application object from extras
//
//
//                return VisitViewModel().myRepository,
//
//                ) as T
//            }
//        }
//    }

}