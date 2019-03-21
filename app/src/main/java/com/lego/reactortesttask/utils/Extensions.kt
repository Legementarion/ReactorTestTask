package com.lego.reactortesttask.utils

import android.app.Activity
import android.content.Context
import android.content.Context.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.SearchView

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
