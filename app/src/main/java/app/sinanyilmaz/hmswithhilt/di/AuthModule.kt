package app.sinanyilmaz.hmswithhilt.di

import com.huawei.agconnect.auth.AGConnectAuth
import com.huawei.agconnect.auth.AGConnectUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object AuthModule {

    @Provides
    fun provideAGConnectAuth(): AGConnectAuth {
        return AGConnectAuth.getInstance()
    }
}