package app.sinanyilmaz.hmswithhilt.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import app.sinanyilmaz.hmswithhilt.repository.HomeRepository

class HomeViewModel @ViewModelInject constructor(private val homeRepository: HomeRepository) :
    BaseViewModel() {

    fun getAnalytics() {
        homeRepository.getAnalytics()
    }

}