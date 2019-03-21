package com.lego.reactortesttask.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lego.reactortesttask.utils.hideKeyboardEx
import com.lego.reactortesttask.utils.toast

abstract class BaseFragment : Fragment() {

    abstract fun getLayout(): Int

    open val viewModel: BaseViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(getLayout(), container, false)

    protected fun toast(text: String) {
        activity?.toast(text)
    }

    protected fun hideKeyboard() {
        activity?.hideKeyboardEx()
    }

}