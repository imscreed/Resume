package com.imscreed.resumeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imscreed.resumeapplication.ui.home.HomeFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }
    }

}
