package com.example.registartion_login.registration.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.coremodule.ViewModelFactory
import com.example.coremodule.findDependency
import com.example.registartion_login.registration.DaggerRegistrationComponent
import com.example.registartion_login.registration.presentation.viewmodel.RegistrationOneTimeEvent
import com.example.registartion_login.registration.presentation.viewmodel.RegistrationViewModel
import com.example.registration_login_api.login.LoginFragmentLauncher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RegistrationFragment : Fragment() {
    private val registrationViewModel by viewModels<RegistrationViewModel> { viewmodelFactory }

    @Inject
    lateinit var viewmodelFactory: ViewModelFactory

    @Inject
    lateinit var loginFragmentLauncher: LoginFragmentLauncher
    override fun onAttach(context: Context) {
        DaggerRegistrationComponent.factory()
            .create(
                context = context,
                loginDependency = context.findDependency()
            ).inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(inflater.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val composeView = view as? ComposeView ?: return
        composeView.setContent { Registration(registrationViewModel) }
        registrationViewModel.eventFlow.onEach {
            when (it) {
                is RegistrationOneTimeEvent.MakeErrorToast -> Toast.makeText(
                    context,
                    it.text,
                    Toast.LENGTH_SHORT
                ).show()

                is RegistrationOneTimeEvent.NavigateToLogin ->
                    loginFragmentLauncher
                        .replaceLoginFragment(parentFragmentManager)
            }
        }.launchIn(lifecycleScope)
    }
}