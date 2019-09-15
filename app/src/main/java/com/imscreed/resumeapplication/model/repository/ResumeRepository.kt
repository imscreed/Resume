package com.imscreed.resumeapplication.model.repository

import android.util.Log
import com.imscreed.resumeapplication.base.BaseRepository
import com.imscreed.resumeapplication.model.api.ResumeApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResumeRepository
    @Inject constructor(private val resumeApi: ResumeApi) : BaseRepository() {

     suspend fun fetchResumeFromRemote(userId: String): String {
         val resume = secureApiCall(
            call = {resumeApi.getResumeFromRemote(userId).await()},
            errorMessage = "Error Fetching Resume"
        )

         return resume.toString()
    }
}
