package com.imscreed.resumeapplication.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.imscreed.resumeapplication.R
import kotlinx.android.synthetic.main.main_fragment.*


class ResumeFragment : Fragment() {

    companion object {
        fun newInstance() = ResumeFragment()
    }

    private lateinit var viewModel: ResumeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ResumeViewModel::class.java)
        viewModel.getResume()


        // Create the observer which updates the UI.
        val resumeObserver = Observer<String> { resume ->
            // Update the UI, in this case, a TextView.
            content_text.text = resume
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.resumeLiveData.observe(this, resumeObserver)
    }

}
