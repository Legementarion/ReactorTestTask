package com.lego.reactortesttask.utils

import android.app.Activity
import android.content.Context
import android.content.Context.*
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.lego.reactortesttask.R

inline val Context.inputMethodManager: InputMethodManager?
    get() = getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.toast(stringRes: Int) {
    Toast.makeText(this, getString(stringRes), Toast.LENGTH_LONG).show()
}

fun Activity.hideKeyboardEx() {
    currentFocus?.apply { inputMethodManager?.hideSoftInputFromWindow(windowToken, 0) }
}

fun Activity.showKeyboardEx(searchView: SearchView) {
    searchView.requestFocus()
    searchView.postDelayed({
        inputMethodManager?.showSoftInput(searchView, 0)
    }, 100)
}

fun Activity.setSoftInputMode(mode: Int) {
    window?.setSoftInputMode(mode)
}

fun Fragment.setSoftInputMode(mode: Int) {
    activity?.setSoftInputMode(mode)
}

fun Context.hideKeyboard(): Boolean {
    return (this as?Activity)?.run {
        currentFocus?.let {
            inputMethodManager?.hideSoftInputFromWindow(it.windowToken, 0)
        } ?: false
    } ?: false
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context)
        .inflate(layoutId, this, attachToRoot)

fun SearchView.onQueryChanged(callback: ((String) -> Unit)) {
    setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String): Boolean {
            callback(query)
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            return true
        }

    })
}

fun Context.createProgressDialog(): AlertDialog {
    return AlertDialog.Builder(this)
        .setView(R.layout.dialog_progress)
        .setCancelable(false)
        .create()
        .apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
}
