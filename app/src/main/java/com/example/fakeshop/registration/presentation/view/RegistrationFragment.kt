package com.example.fakeshop.registration.presentation.view

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
import com.example.fakeshop.R
import com.example.fakeshop.appComponent
import com.example.fakeshop.login.presentation.view.LoginFragment
import com.example.fakeshop.productlist.presentation.viewModel.ViewModelFactory
import com.example.fakeshop.registration.presentation.viewmodel.RegistrationOneTimeEvent
import com.example.fakeshop.registration.presentation.viewmodel.RegistrationViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RegistrationFragment : Fragment() {
    private val registrationViewModel by viewModels<RegistrationViewModel> { viewmodelFactory }

    @Inject
    lateinit var viewmodelFactory: ViewModelFactory
    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
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

                is RegistrationOneTimeEvent.NavigateToLogin -> parentFragmentManager.beginTransaction()
                    .replace(R.id.main, LoginFragment())
                    .commit()
            }
        }.launchIn(lifecycleScope)

    }
}