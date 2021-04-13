package com.example.soccert.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.soccert.R
import com.example.soccert.utils.ToastType
import com.example.soccert.utils.showToast

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    @get: LayoutRes
    protected abstract val layoutResource: Int

    abstract val viewModel: RxViewModel

    private var _binding: T? = null

    protected val binding: T
        get() = _binding ?: throw IllegalStateException(EXCEPTION)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil
        .inflate<T>(inflater, layoutResource, container, false)
        .apply { _binding = this }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.error.observe(viewLifecycleOwner, Observer {
            view.context.showToast(ToastType.Error, getString(R.string.error_default))
        })
        initData()
        initActions()
    }

    protected abstract fun initData()

    protected abstract fun initActions()

    fun Fragment.getNavController(): NavController? {
        return try {
            NavHostFragment.findNavController(this)
        } catch (e: IllegalStateException) {
            null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val EXCEPTION = "DataBinding only is valid after onCreateView"
    }
}
