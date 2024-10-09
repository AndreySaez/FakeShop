package com.example.fakeshop.registration.presentation.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fakeshop.R
import com.example.fakeshop.registration.presentation.viewmodel.RegisterState
import com.example.fakeshop.registration.presentation.viewmodel.RegistrationAction
import com.example.fakeshop.registration.presentation.viewmodel.RegistrationViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(
    showSystemUi = true,
    showBackground = true
)
private fun RegistrationPreview() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        RegistrationColumn()
    }
}

@Composable
fun Registration(
    viewModel: RegistrationViewModel
) {
    val state by viewModel.state.collectAsState()
    RegistrationColumn(
        registerState = state,
        onAction = {
            viewModel.onAction(it)
        }
    )
}

@Composable
private fun RegistrationColumn(
    registerState: RegisterState = RegisterState.INITIAL,
    onAction: (RegistrationAction) -> Unit = {}
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xff121212))
            .padding(32.dp)
    ) {
        Text(
            color = colorResource(id = R.color.white),
            fontSize = 25.sp,
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.registration),
        )
        Spacer(modifier = Modifier.padding(36.dp))
        RegisterInputTextField(
            title = stringResource(id = R.string.name),
            value = registerState.registerForm.name,
            onTextChanged = {
                onAction(RegistrationAction.OnNameChanged(it))
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        RegisterInputTextField(
            title = stringResource(id = R.string.email),
            value = registerState.registerForm.email,
            onTextChanged = {
                onAction(RegistrationAction.OnEmailChanged(it))
            },
            isError = registerState.isEmailWrong

        )
        Spacer(modifier = Modifier.padding(8.dp))
        RegisterInputTextField(
            title = stringResource(id = R.string.password),
            value = registerState.registerForm.password,
            isPassword = true,
            onTextChanged = {
                onAction(RegistrationAction.OnPasswordChanged(it))
            },
            isError = registerState.isPasswordWrong
        )
        Spacer(modifier = Modifier.padding(30.dp))

        EnterButton(registerState.isLoading, onAction)
        Spacer(modifier = Modifier.padding(25.dp))
        ClickableText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(
                color = Color.White,
                fontSize = 14.sp
            ),
            text = AnnotatedString(
                stringResource(id = R.string.already_have_account)
            ),
            onClick = {
                onAction(RegistrationAction.haveAccountClicked)
            }
        )

    }
}

@Composable
private fun RegisterInputTextField(
    title: String,
    isPassword: Boolean = false,
    value: String = "",
    onTextChanged: (String) -> Unit = {},
    isError: Boolean = false
) {
    var text by rememberSaveable { mutableStateOf(value) }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xff262624),
            unfocusedContainerColor = Color(0xff262624),
            disabledContainerColor = Color(0xff262624),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.LightGray,
            disabledTextColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        isError = isError,
        singleLine = true,
        shape = Shapes().small,
        value = text,
        onValueChange = {
            text = it
            onTextChanged(text)
        },
        placeholder = {
            Text(text = title, color = Color(0xff9D9D9D))
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default
    )
}

@Composable
private fun EnterButton(
    isLoading: Boolean = false,
    onClick: (RegistrationAction) -> Unit = {}
) {
    val isButtonEnabled by remember { mutableStateOf(!isLoading) }

    Button(
        content = {
            if (isLoading) {
                CircularProgressIndicator()
            } else
                Text(
                    fontSize = 17.sp,
                    text = stringResource(R.string.registrate)
                )

        },
        onClick = { onClick(RegistrationAction.EnterClick) },
        colors = ButtonColors(
            containerColor = Color(0xff31AFF5),
            contentColor = Color.Black,
            disabledContainerColor = Color(0xff31AFF5),
            disabledContentColor = Color.Black,

            ),
        enabled = isButtonEnabled,
        modifier = Modifier.fillMaxWidth(),
        shape = Shapes().medium
    )
}

