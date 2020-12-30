package app.sinanyilmaz.hmswithhilt.di

import android.content.Context
import com.huawei.agconnect.auth.AGConnectAuth
import com.huawei.agconnect.auth.AGConnectUser
import com.huawei.hms.kit.awareness.Awareness
import com.huawei.hms.kit.awareness.CaptureClient
import com.huawei.hms.kit.awareness.capture.HeadsetStatusResponse
import com.huawei.hms.kit.awareness.capture.ScreenStatusResponse
import com.huawei.hms.kit.awareness.capture.WeatherStatusResponse
import com.huawei.hms.kit.awareness.status.HeadsetStatus
import com.huawei.hms.kit.awareness.status.WeatherStatus
import com.huawei.hms.kit.awareness.status.weather.WeatherSituation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object AwarenessModule {

    @Provides
    fun provideAwarenessCaptureClient(@ApplicationContext context: Context): CaptureClient {
       return Awareness.getCaptureClient(context)
    }
}