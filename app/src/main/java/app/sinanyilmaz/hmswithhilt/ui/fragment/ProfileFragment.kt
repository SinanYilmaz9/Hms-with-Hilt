package app.sinanyilmaz.hmswithhilt.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import app.sinanyilmaz.hmswithhilt.databinding.FragmentProfileBinding
import app.sinanyilmaz.hmswithhilt.viewmodel.ProfileViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val TAG = ProfileFragment::class.java.simpleName

    private val viewModel: ProfileViewModel by viewModels()
    private val glide by lazy { Glide.with(requireContext()) }

    override fun setViewBinding(): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLogin()
        initObservers()
    }

    private fun initObservers() {
        viewModel._currentUser.observe(viewLifecycleOwner) {
            viewBinding.nameProfile.text = it.uid
        }
    }

   /* private fun initClickListeners() {
        viewBinding.buttonLogin.setOnClickListener {
            viewModel.getLogin(requireContext())
        }
        viewBinding.buttonSignOut.setOnClickListener {
            viewModel.getSignOut()
        }
    }*/
}