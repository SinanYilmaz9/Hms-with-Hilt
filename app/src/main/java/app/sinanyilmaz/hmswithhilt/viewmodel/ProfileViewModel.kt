package app.sinanyilmaz.hmswithhilt.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.sinanyilmaz.hmswithhilt.repository.ProfileRepository
import com.huawei.agconnect.auth.AGConnectUser

class ProfileViewModel @ViewModelInject constructor(
    private val profileRepository: ProfileRepository) : BaseViewModel() {

    val currentUser = MutableLiveData<AGConnectUser>()
    val _currentUser: LiveData<AGConnectUser> = currentUser

    fun getLogin() {
        profileRepository.loginWithAnonymous()?.addOnCompleteListener {
            if (it.isSuccessful) {
                currentUser.value = it.result.user
            } else {

            }
        }
    }
}
