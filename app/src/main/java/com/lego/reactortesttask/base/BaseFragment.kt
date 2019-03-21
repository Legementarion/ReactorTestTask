package com.lego.reactortesttask.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.lego.reactortesttask.utils.hideKeyboardEx
import com.lego.reactortesttask.utils.toast

abstract class BaseFragment<BINDING : ViewDataBinding> : Fragment() {

    abstract fun getLayout(): Int

    open val viewModel: BaseViewModel? = null
    protected lateinit var binding: BINDING

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        return binding.root
    }

    protected fun toast(text: String) {
        activity?.toast(text)
    }

    protected fun hideKeyboard() {
        activity?.hideKeyboardEx()
    }

}