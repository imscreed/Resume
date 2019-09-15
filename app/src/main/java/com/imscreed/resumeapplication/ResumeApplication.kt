package com.imscreed.resumeapplication

import android.app.Application
import com.imscreed.resumeapplication.di.ApplicationComponent
import com.imscreed.resumeapplication.di.ApplicationModule
import com.imscreed.resumeapplication.di.DaggerApplicationComponent

class ResumeApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)

}