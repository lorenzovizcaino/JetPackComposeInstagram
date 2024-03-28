package com.antonio.jetpackcomposeinstagram

import android.app.Activity
import android.graphics.Paint.Align
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(
            Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        )
        Footer(
            Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )
    }

}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier) {
        Divider(Modifier.background(Color(0xFFF9F9F9)))
        Spacer(modifier = Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = "Dont't have an account?", color = Color(0xFFB5B5B5), fontSize = 14.sp)
            Spacer(modifier = Modifier.size(25.dp))
            Text(
                text = "Sign Up.",
                color = Color(0xFF4EA8E9),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.clickable { })

        }
        Spacer(modifier = Modifier.size(25.dp))


    }

}

@Composable
fun Body(modifier: Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var loginenabled by rememberSaveable { mutableStateOf(false) }
    Column(modifier = modifier) {
        ImageLogo(modifier)
        Spacer(modifier = Modifier.size(16.dp))
        Email(email, { email = it
            loginenabled= enabledLogin(email,password)
        }, modifier)
        Spacer(modifier = Modifier.size(4.dp))
        Password(password, { password = it
            loginenabled= enabledLogin(email, password)
        }, modifier)
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword()
        Spacer(modifier = Modifier.size(16.dp))

        LoginButton(loginenabled, modifier)
        Spacer(modifier = Modifier.size(16.dp))
        DivisorOr(modifier)
        Spacer(modifier = Modifier.size(32.dp))
        LoginSocial(modifier)
        Spacer(modifier = Modifier.size(10.dp))


    }

}

@Composable
fun LoginSocial(modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "logo Facebook",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Continue as Antonio",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp),
            color = Color(0xFF4EA8E9)
        )

    }

}

@Composable
fun DivisorOr(modifier: Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )


    }

}

@Composable
fun LoginButton(loginenabled: Boolean, modifier: Modifier) {
    Button(
        onClick = {},
        enabled = loginenabled,
        modifier = modifier,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ){
        Text(text = "Log in")
    }

}

fun enabledLogin(email:String,password:String):Boolean{
    return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
    //Con Patterns nos comprueba que sea un email, un telefono, un dominio, una URL, etc.
}

@Composable
fun ForgotPassword() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Forgot password?",
        textAlign = TextAlign.End,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Color(0xFF4EA8E9)
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChanged: (String) -> Unit, modifier: Modifier) {
    var passwordVisibility by rememberSaveable {
        mutableStateOf(false)
    }
    TextField(
        value = password,
        onValueChange = { onTextChanged(it) },
        modifier = modifier,
        placeholder = { Text(text = "Password")},
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(

            textColor = Color(0xFFB2B2B2),
            focusedIndicatorColor =  Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent

        ),
        trailingIcon = {
            val image = if(passwordVisibility){
                Icons.Filled.VisibilityOff
            }else{
                Icons.Filled.Visibility
            }
            IconButton(onClick = {passwordVisibility=!passwordVisibility}) {
                Icon(imageVector = image, contentDescription ="show Password" )
                
            }
        },
        visualTransformation = if(passwordVisibility) {
            VisualTransformation.None
        }else{
            PasswordVisualTransformation()
        }

    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChanged: (String) -> Unit, modifier: Modifier) {
    TextField(
        value = email,
        onValueChange = { onTextChanged(it) },
        modifier = modifier,
        placeholder = { Text(text = "Email")},
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(

            textColor = Color(0xFFB2B2B2),
            focusedIndicatorColor =  Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent

        )
    )
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "logo",
        modifier = modifier.size(50.dp)
    )
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    //En Android normal cuando queremos cerrar una aplicacion se pone "finish"
    //en este caso entonces  se coge el contexto y se convierte a Activity y asi si nos deja utilizar el "finish"

    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "close app",
        modifier = modifier.clickable { activity.finish() })
}
