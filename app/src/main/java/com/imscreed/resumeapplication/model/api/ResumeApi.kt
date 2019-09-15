package com.imscreed.resumeapplication.model.api

import com.imscreed.resumeapplication.model.data.ResumeDataModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ResumeApi {
    @GET("/{userId}/c9dcfa1b37dec07fb2ee7f36d7278105/raw/83f37d67ec02953918eb60821c0205f527428714/resume.json")
    fun getResumeFromRemote(@Path("userId") userId: String): Deferred<Response<ResumeDataModel>>
}