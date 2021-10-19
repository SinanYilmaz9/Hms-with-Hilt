package app.sinanyilmaz.hmswithhilt.di

import android.content.Context
import com.huawei.hms.kit.awareness.Awareness
import com.huawei.hms.kit.awareness.CaptureClient
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