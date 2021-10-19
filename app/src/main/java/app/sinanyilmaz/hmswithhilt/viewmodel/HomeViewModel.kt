package app.sinanyilmaz.hmswithhilt.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.sinanyilmaz.hmswithhilt.repository.HomeRepository
import com.huawei.agconnect.auth.AGConnectUser
import com.huawei.hms.kit.awareness.status.ScreenStatus

class HomeViewModel @ViewModelInject constructor(private val homeRepository: HomeRepository) :
    BaseViewModel() {

    val screenStatus = MutableLiveData<ScreenStatus>()
    val _screenStatus: LiveData<ScreenStatus> = screenStatus

    fun getAnalytics() {
        homeRepository.getAnalytics()
    }

    fun getAwarenessCapture() {
        homeRepository.getAwarenessScreenStatus().addOnCompleteListener {
            if (it.isSuccessful) {
                screenStatus.value = it.result.screenStatus
            } else {
                Log.e("TAG", "Screen state failed")
            }
        }
    }
}