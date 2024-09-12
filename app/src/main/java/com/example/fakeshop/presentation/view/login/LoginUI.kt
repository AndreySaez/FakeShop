package com.example.fakeshop.presentation.view.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fakeshop.R
import com.example.fakeshop.presentation.viewModel.LoginState
import com.example.fakeshop.presentation.viewModel.LoginViewModel
import com.example.fakeshop.presentation.viewModel.action.LoginAction

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun LoginPreview() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        LoginColumn()
    }

}

@Composable
fun Login(
    viewModel: LoginViewModel
) {
    val state by viewModel.state.collectAsState()
    LoginColumn(
        onAction = {
            viewModel.onAction(it)
        },
        loginState = state
    )
}

@Composable
private fun LoginColumn(
    onAction: (LoginAction) -> Unit = {},
    loginState: LoginState = LoginState.INITIAL
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff121212))
            .padding(32.dp),
    ) {
        Text(
            color = colorResource(id = R.color.white),
            fontSize = 25.sp,
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.login),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        LoginInputTextField(
            title = stringResource(id = R.string.email),
            value = loginState.loginForm.email,
            onTextChanged = {
                onAction(LoginAction.OnEmailChanged(it))
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        LoginInputTextField(
            title = stringResource(id = R.string.password),
            value = loginState.loginForm.password,
            onTextChanged = {
                onAction(LoginAction.OnPasswordChanged(it))
            },
            isPassword = true
        )
        Spacer(modifier = Modifier.padding(130.dp))
        EnterButton(
            isLoading = loginState.isLoading,
            onClick = onAction
        )
    }
}

@Composable
private fun LoginInputTextField(
    title: String,
    isPassword: Boolean = false,
    value: String = "",
    onTextChanged: (String) -> Unit = {},
) {
    var text by rememberSaveable { mutableStateOf(value) }
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier
            .width(335.dp)
            .height(55.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xff262624),
            unfocusedContainerColor = Color(0xff262624),
            disabledContainerColor = Color(0xff262624),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.LightGray,
            errorIndicatorColor = Color.Red,
            disabledTextColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            errorTextColor = Color.White
        ),
        singleLine = true,
        trailingIcon = {
            if (isPassword) {
                TextButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    Text(
                        text = if (isPasswordVisible) "Hide" else "Show",
                        color = Color(0xff9D9D9D)
                    )
                }
            }
        },
        shape = Shapes().small,
        value = text,
        onValueChange = {
            text = it
            onTextChanged(text)
        },
        placeholder = {
            Text(text = title, color = Color(0xff9D9D9D))
        },
        visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default
    )
}

@Composable
private fun EnterButton(
    isLoading: Boolean = true,
    onClick: (LoginAction) -> Unit = {}
) {
    val isButtonEnabled by remember { mutableStateOf(!isLoading) }

    Button(
        content = {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Text(text = stringResource(R.string.login))
            }
        },
        onClick = { onClick(LoginAction.EnterClick) },
        colors = ButtonColors(
            containerColor = Color(0xff31AFF5),
            contentColor = Color.Black,
            disabledContainerColor = Color(0xff31AFF5),
            disabledContentColor = Color.Black,

            ),
        enabled = isButtonEnabled,
        modifier = Modifier.width(335.dp),
        shape = Shapes().medium
    )
}