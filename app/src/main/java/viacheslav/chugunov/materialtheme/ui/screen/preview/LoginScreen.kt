package viacheslav.chugunov.materialtheme.ui.screen.preview

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import viacheslav.chugunov.core.model.domain.Theme
import viacheslav.chugunov.materialtheme.R
import viacheslav.chugunov.materialtheme.extension.*
import viacheslav.chugunov.materialtheme.ui.theme.LocalTheme

@Composable
fun LoginScreen() {
    val theme = LocalTheme.current
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    DrawScreen(
        theme = theme,
        login = login,
        password = password,
        onLoginChanged = { login = it },
        onPasswordChanged = { password = it }
    )
}

@Composable
private fun DrawScreen(
    theme: Theme,
    login: String,
    password: String,
    onLoginChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            OutlinedTextField(
                value = login,
                onValueChange = onLoginChanged,
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = theme.onBackground,
                    focusedBorderColor = theme.secondaryRegular,
                    unfocusedBorderColor = theme.secondaryLight,
                    cursorColor = theme.secondaryDark
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChanged,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = theme.onBackground,
                    focusedBorderColor = theme.secondaryRegular,
                    unfocusedBorderColor = theme.secondaryLight,
                    cursorColor = theme.secondaryDark
                )
            )
        }
    }
}