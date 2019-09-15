package com.imscreed.resumeapplication.model.repository

import com.imscreed.resumeapplication.base.BaseRepository
import com.imscreed.resumeapplication.model.api.ResumeApi
import com.imscreed.resumeapplication.model.data.ResumeDataModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResumeRepository
@Inject constructor(private val resumeApi: ResumeApi) : BaseRepository() {

    suspend fun fetchResumeFromRemote(userId: String): ResumeDataModel? = secureApiCall(
        call = { resumeApi.getResumeFromRemote(userId).await() },
        errorMessage = "Error Fetching Resume"
    )
}
