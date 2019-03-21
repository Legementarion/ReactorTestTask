package com.lego.reactortesttask.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lego.reactortesttask.R
import com.lego.reactortesttask.flow.giphy.GiphyFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, GiphyFragment(), GiphyFragment.TAG)
            .commit()
    }
}
