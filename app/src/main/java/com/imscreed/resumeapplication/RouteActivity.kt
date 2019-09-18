package com.imscreed.resumeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imscreed.resumeapplication.ui.resume.BiometricAuthFragment
import com.imscreed.resumeapplication.ui.resume.ResumeFragment

class RouteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.route_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BiometricAuthFragment.newInstance())
                .commitNow()
        }
    }

}
