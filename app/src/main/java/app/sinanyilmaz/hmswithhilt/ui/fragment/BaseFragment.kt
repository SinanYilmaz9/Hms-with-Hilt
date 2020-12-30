package app.sinanyilmaz.hmswithhilt.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    lateinit var viewBinding: T
    abstract fun setViewBinding(): T
    lateinit var mFragmentNavigation: FragmentNavigation
    var baseContext: Context? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = setViewBinding()
        return viewBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseContext = context
        mFragmentNavigation = context as FragmentNavigation
    }


    interface FragmentNavigation {
        fun giveAction(action: Int)
        fun navigateUP()
    }
}