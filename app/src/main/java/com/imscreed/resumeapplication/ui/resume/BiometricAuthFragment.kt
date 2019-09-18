package com.imscreed.resumeapplication.ui.resume

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.Fragment
import com.imscreed.resumeapplication.R
import kotlinx.android.synthetic.main.fragment_biometric_auth.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class BiometricAuthFragment : Fragment() {

    companion object {
        fun newInstance() = BiometricAuthFragment()
    }

    private val TAG = BiometricAuthFragment.javaClass.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_biometric_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authenticateButton.setOnClickListener {
            val biometricManager = BiometricManager.from(requireContext())
            when (biometricManager.canAuthenticate()) {
                BiometricManager.BIOMETRIC_SUCCESS -> {
                    Log.d(TAG, "App can authenticate using biometrics.")
                    showBiometricPrompt()
                }
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                    Log.e(TAG, "No biometric features available on this device.")
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                    Log.e(TAG, "Biometric features are currently unavailable.")
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    Log.e(
                        TAG, "The user hasn't associated any biometric credentials " +
                                "with their account."
                    )
                    // For emulator to work
                    moveToResumeFragment()
                }

            }
        }
    }

    fun moveToResumeFragment() {
        val fm = fragmentManager
        val fragmentTransaction = fm!!.beginTransaction()
        fragmentTransaction.replace(R.id.container, ResumeFragment.newInstance())
        fragmentTransaction.commit()
    }

    private fun showBiometricPrompt() {
        val executor = Executors.newSingleThreadExecutor()

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.biometric_title))
            .setSubtitle(getString(R.string.biometric_message))
            .setDescription("By authenticating you will be given privilege to view confidential data")
            .setNegativeButtonText(getString(R.string.cancel))
            .build()

        val biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    moveToResumeFragment()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }
            })

        biometricPrompt.authenticate(promptInfo)
    }

    internal class MainThreadExecutor : Executor {
        private val handler = Handler(Looper.getMainLooper())

        override fun execute(r: Runnable) {
            handler.post(r)
        }
    }
}
