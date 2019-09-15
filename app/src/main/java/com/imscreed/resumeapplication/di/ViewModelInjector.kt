package com.imscreed.resumeapplication.di

import com.imscreed.resumeapplication.ui.resume.ResumeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified ResumeViewModel.
     * @param resumeViewModel ResumeViewModel in which to inject the dependencies
     */
    fun inject(resumeViewModel: ResumeViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun appModule(applicationModule: ApplicationModule): Builder
    }
}