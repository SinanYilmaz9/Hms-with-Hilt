package app.sinanyilmaz.hmswithhilt.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import app.sinanyilmaz.hmswithhilt.R
import app.sinanyilmaz.hmswithhilt.databinding.ActivityMainBinding
import app.sinanyilmaz.hmswithhilt.ui.fragment.BaseFragment
import app.sinanyilmaz.hmswithhilt.util.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BaseFragment.FragmentNavigation {

    private lateinit var binding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null
    private var bottomNavigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
        supportActionBar?.hide()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        bottomNavigationView = binding.bottomNav

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
        currentNavController = controller
    }


    override fun giveAction(action: Int) {
        currentNavController?.value?.navigate(action)
    }

    override fun navigateUP() {
        currentNavController?.value?.navigateUp()
    }
}