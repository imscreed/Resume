package com.imscreed.resumeapplication.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.imscreed.resumeapplication.AppConstants.userId
import com.imscreed.resumeapplication.base.BaseViewModel
import com.imscreed.resumeapplication.model.data.ResumeDataModel
import com.imscreed.resumeapplication.model.repository.ResumeRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ResumeViewModel: BaseViewModel(){
    @Inject
    lateinit var repository: ResumeRepository

    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    val resumeLiveData = MutableLiveData<String>()

    fun getResume(){
        scope.launch {
            val resume = repository.fetchResumeFromRemote(userId)
            resumeLiveData.postValue(resume)
        }
    }

    fun cancelAllRequests() = coroutineContext.cancel()
}
