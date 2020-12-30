package app.sinanyilmaz.hmswithhilt.repository

import com.huawei.agconnect.auth.AGConnectAuth
import com.huawei.agconnect.auth.AGConnectUser
import com.huawei.agconnect.auth.SignInResult
import com.huawei.hmf.tasks.Task
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val agConnectAuth: AGConnectAuth) {

    fun loginWithAnonymous(): Task<SignInResult>? {
        return agConnectAuth.signInAnonymously()
    }
}