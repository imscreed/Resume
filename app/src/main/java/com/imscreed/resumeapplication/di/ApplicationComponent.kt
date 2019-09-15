package com.imscreed.resumeapplication.di

import com.imscreed.resumeapplication.ResumeApplication
import com.imscreed.resumeapplication.HomeActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: ResumeApplication)
    fun inject(homeActivity: HomeActivity)
}