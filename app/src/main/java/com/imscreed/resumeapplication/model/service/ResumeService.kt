package com.imscreed.resumeapplication.model.service

import com.imscreed.resumeapplication.model.api.ResumeApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResumeService
@Inject constructor(retrofit: Retrofit): ResumeApi {
    private val resumeApi by lazy { retrofit.create(ResumeApi::class.java) }

    override fun getResumeFromRemote(userId: String) = resumeApi.getResumeFromRemote(userId)
}
