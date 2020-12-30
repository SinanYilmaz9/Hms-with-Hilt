package app.sinanyilmaz.hmswithhilt.repository

import com.huawei.hms.analytics.HiAnalyticsInstance
import javax.inject.Inject

class HomeRepository @Inject constructor(private val analytics: HiAnalyticsInstance) {

    fun getAnalytics(): HiAnalyticsInstance {
        return analytics
    }
}
