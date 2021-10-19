package app.sinanyilmaz.hmswithhilt.slider

import android.util.Log
import android.view.SurfaceHolder
import androidx.core.util.forEach
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.huawei.hms.mlsdk.common.MLAnalyzer
import com.huawei.hms.mlsdk.face.MLFace

class FaceAnalyzerTransactor : MLAnalyzer.MLTransactor<MLFace?> {
    private var mOverlay: SurfaceHolder? = null

    private var leftProbability: String? = null
    private var rightProbability: String? = null
    private var leftOpenness: String? = null
    private var rightOpenness: String? = null
    private var leftPoint: String? = null
    private var rightPoint: String? = null

    private val resultLiveData = MutableLiveData<ResultData>()
    val _resultLiveData: LiveData<ResultData> = resultLiveData


    fun setOverlay(surfaceHolder: SurfaceHolder) {
        mOverlay = surfaceHolder
    }

    override fun transactResult(results: MLAnalyzer.Result<MLFace?>) {
        val items = results.analyseList
        items.forEach { key, value ->

            value?.apply {
                leftProbability = this.features?.leftEyeOpenProbability.toString()
                rightProbability = this.features?.rightEyeOpenProbability.toString()

                leftOpenness = this.opennessOfLeftEye().toString()
                rightOpenness = this.opennessOfRightEye().toString()

                leftPoint= this.faceKeyPoints!![4].point.toString()
                rightPoint= this.faceKeyPoints!![10].point.toString()
            }

            resultLiveData.postValue(ResultData(leftProbability!!,rightProbability!!,leftOpenness!!,rightOpenness!!,leftPoint!!,rightPoint!!))
        }

    }

    override fun destroy() {}
}