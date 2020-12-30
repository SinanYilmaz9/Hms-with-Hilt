package app.sinanyilmaz.hmswithhilt.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import app.sinanyilmaz.hmswithhilt.databinding.FragmentHomeBinding
import app.sinanyilmaz.hmswithhilt.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun setViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }
}