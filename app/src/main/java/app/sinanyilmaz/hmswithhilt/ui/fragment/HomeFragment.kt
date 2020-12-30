package app.sinanyilmaz.hmswithhilt.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import app.sinanyilmaz.hmswithhilt.databinding.FragmentHomeBinding
import app.sinanyilmaz.hmswithhilt.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    override fun setViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAwarenessCapture()
        initObservers()
    }

    private fun initObservers() {
        viewModel._screenStatus.observe(viewLifecycleOwner) {

        }
    }
}