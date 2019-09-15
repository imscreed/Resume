package com.imscreed.resumeapplication.base

import androidx.lifecycle.ViewModel
import com.imscreed.resumeapplication.ResumeApplication
import com.imscreed.resumeapplication.di.ApplicationModule
import com.imscreed.resumeapplication.di.DaggerViewModelInjector
import com.imscreed.resumeapplication.di.ViewModelInjector
import com.imscreed.resumeapplication.ui.resume.ResumeViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .appModule(ApplicationModule(ResumeApplication()))
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ResumeViewModel -> injector.inject(this)
        }
    }
}