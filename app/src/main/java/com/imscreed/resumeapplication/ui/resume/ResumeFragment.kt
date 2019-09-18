package com.imscreed.resumeapplication.ui.resume

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.imscreed.resumeapplication.R
import com.imscreed.resumeapplication.model.data.ResumeDataModel
import com.imscreed.resumeapplication.util.ViewUtil
import kotlinx.android.synthetic.main.resume_fragment.*


class ResumeFragment : Fragment() {

    companion object {
        fun newInstance() = ResumeFragment()
    }

    private lateinit var viewModel: ResumeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.resume_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ResumeViewModel::class.java)
        viewModel.getResume()

        val resumeObserver = Observer<ResumeDataModel> { resume ->
            content_text.text = ViewUtil.getStylizedResume(requireContext(), resume)
        }

        viewModel.resumeLiveData.observe(this, resumeObserver)
    }
}
