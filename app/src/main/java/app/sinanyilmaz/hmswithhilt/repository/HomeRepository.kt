package app.sinanyilmaz.hmswithhilt.repository

import com.huawei.hmf.tasks.Task
import com.huawei.hms.analytics.HiAnalyticsInstance
import com.huawei.hms.kit.awareness.CaptureClient
import com.huawei.hms.kit.awareness.capture.ScreenStatusResponse
import com.huawei.hms.kit.awareness.capture.WeatherStatusResponse
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val analytics: HiAnalyticsInstance,
    private val awarenessCaptureClient: CaptureClient
) {

    fun getAnalytics(): HiAnalyticsInstance {
        return analytics
    }

    fun getAwarenessScreenStatus(): Task<ScreenStatusResponse> {
        return awarenessCaptureClient.screenStatus
    }
}
