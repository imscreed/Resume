package com.imscreed.resumeapplication.ui.resume

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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

    val resumeLiveData = MutableLiveData<ResumeDataModel>()

    fun getResume() {
        viewModelScope.launch {
            val resume = repository.fetchResumeFromRemote(userId)
            save(resume)
        }
    }

    fun save(resume: ResumeDataModel?) {
        resumeLiveData.postValue(resume)
    }
}
