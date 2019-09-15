package com.imscreed.resumeapplication.model.api

import com.imscreed.resumeapplication.model.data.ResumeDataModel
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ResumeApi {
    // Sample userId = thomasdavis/c9dcfa1b37dec07fb2ee7f36d7278105/raw/83f37d67ec02953918eb60821c0205f527428714
    @GET("/{userId}/resume.json")
    fun getResume(@Path("userId") userId: String): ResumeDataModel
}