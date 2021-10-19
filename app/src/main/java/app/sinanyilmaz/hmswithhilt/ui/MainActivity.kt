package app.sinanyilmaz.hmswithhilt.ui

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.PixelFormat
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import app.sinanyilmaz.hmswithhilt.R
import app.sinanyilmaz.hmswithhilt.databinding.ActivityMainBinding
import app.sinanyilmaz.hmswithhilt.slider.*
import app.sinanyilmaz.hmswithhilt.ui.fragment.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.huawei.hms.mlsdk.MLAnalyzerFactory
import com.huawei.hms.mlsdk.common.LensEngine
import com.huawei.hms.mlsdk.face.MLFaceAnalyzer
import com.huawei.hms.mlsdk.face.MLFaceAnalyzerSetting
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseFragment.FragmentNavigation {

    private lateinit var binding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null
    private var bottomNavigationView: BottomNavigationView? = null

    private lateinit var mAnalyzer: MLFaceAnalyzer
    private var surfaceHolderCamera: SurfaceHolder? = null
    private var surfaceHolderOverlay: SurfaceHolder? = null
    private lateinit var mLensEngine: LensEngine
    private lateinit var mFaceAnalyzerTransactor: FaceAnalyzerTransactor

    private lateinit var rvHorizontalPicker: RecyclerView

    private val data = ArrayList<Char>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
        supportActionBar?.hide()

        initAlphabet()

        mAnalyzer = createAnalyzer()
        prepareViews()
        mFaceAnalyzerTransactor = FaceAnalyzerTransactor()
        mAnalyzer.setTransactor(mFaceAnalyzerTransactor)
        setHorizontalPicker()
        observeResult()
    }

    private fun initAlphabet() {
        var c = 'A'
        while (c <= 'Z') {
            print("$c ")
            data.add(c)
            ++c
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun prepareViews() {
        surfaceHolderCamera = binding.surfaceViewCamera.holder
        surfaceHolderOverlay = binding.surfaceViewOverlay.holder

        surfaceHolderOverlay?.setFormat(PixelFormat.TRANSPARENT)
        surfaceHolderCamera?.addCallback(surfaceHolderCallback)
    }

    private fun createAnalyzer(): MLFaceAnalyzer {
        val settings = MLFaceAnalyzerSetting.Factory()
            .allowTracing()
            .setFeatureType(MLFaceAnalyzerSetting.TYPE_FEATURES)
            .setShapeType(MLFaceAnalyzerSetting.TYPE_SHAPES)
            .setMinFaceProportion(.5F)
            .setKeyPointType(MLFaceAnalyzerSetting.TYPE_KEYPOINTS)
            .create()

        return MLAnalyzerFactory.getInstance().getFaceAnalyzer(settings)
    }

    private val surfaceHolderCallback = object : SurfaceHolder.Callback {
        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            mLensEngine = createLensEngine(width, height)
            surfaceHolderOverlay?.let {
                mFaceAnalyzerTransactor.setOverlay(it)
                mLensEngine.run(holder)
            }
        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {
            mLensEngine.release()
        }

        override fun surfaceCreated(holder: SurfaceHolder) {}
    }

    private fun createLensEngine(width: Int, height: Int): LensEngine {
        val lensEngineCreator = LensEngine.Creator(this, mAnalyzer)
            .applyFps(20F)
            .setLensType(LensEngine.FRONT_LENS)
            .enableAutomaticFocus(true)

        return if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            lensEngineCreator.let {
                it.applyDisplayDimension(height, width)
                it.create()
            }
        } else {
            lensEngineCreator.let {
                it.applyDisplayDimension(width, height)
                it.create()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeResult() {
        mFaceAnalyzerTransactor._resultLiveData.observe(this, Observer {

            binding.leftProb.text = "Left Probability: ${it.leftProbability}"
            binding.rightProb.text = "Right Probability: ${it.rightProbability}"
            binding.leftOpenness.text = "Left Openness: ${it.leftOpenness}"
            binding.rightOpenness.text ="Right Openness: ${it.rightOpenness}"
            binding.leftPoint.text ="Left Point: ${it.leftPoint}"
            binding.rightPoint.text ="Right Point: ${it.rightPoint}"
        })
    }


    private fun setupBottomNavigationBar() {
        /*     bottomNavigationView = binding.bottomNav

        val navGraphIds = listOf(
            R.navigation.home,
            R.navigation.profile
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView!!.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
            NavigationUI.setupActionBarWithNavController(this, navController)
        })
        currentNavController = controller*/
    }


    override fun giveAction(action: Int) {
        currentNavController?.value?.navigate(action)
    }

    override fun navigateUP() {
        currentNavController?.value?.navigateUp()
    }

    private fun setHorizontalPicker() {
        rvHorizontalPicker = findViewById(R.id.rv_horizontal_picker)

        // Setting the padding such that the items will appear in the middle of the screen
        val padding: Int = ScreenUtils.getScreenWidth(this) / 2 - ScreenUtils.dpToPx(this, 40)
        rvHorizontalPicker.setPadding(padding, 0, padding, 0)

        // Setting layout manager
        rvHorizontalPicker.layoutManager = SliderLayoutManager(this).apply {
            callback = object : SliderLayoutManager.OnItemSelectedListener {
                override fun onItemSelected(layoutPosition: Int) {

                }
            }
        }

        // Setting Adapter
        rvHorizontalPicker.adapter = SliderAdapter().apply {
            setData(data)
            callback = object : SliderAdapter.Callback {
                override fun onItemClicked(view: View) {
                    rvHorizontalPicker.smoothScrollToPosition(
                        rvHorizontalPicker.getChildLayoutPosition(
                            view
                        )
                    )
                }
            }
        }
    }
}

