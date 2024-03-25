package com.antonio.jetpackcomposeinstagram

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(50.dp)) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center).fillMaxWidth())
    }

}

@Composable
fun Body(modifier: Modifier) {
    var email by rememberSaveable {mutableStateOf("")}
    var password by rememberSaveable {mutableStateOf("")}
    Column(modifier = modifier){
        ImageLogo()
        Spacer(modifier = Modifier.size(16.dp))
        Email(email,{email=it},modifier)
        Spacer(modifier = Modifier.size(4.dp))
        Password(password,{password=it},modifier)
        Spacer(modifier = Modifier.size(8.dp))
        ForgotPassword()
    }

}

@Composable
fun ForgotPassword() {
    Text(modifier=Modifier.fillMaxWidth(),text = "Forgot password?", textAlign = TextAlign.End, color= Color.Blue)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChanged: (String) -> Unit, modifier: Modifier) {
    TextField(value = password , onValueChange = { onTextChanged(it)},modifier=modifier )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChanged: (String) -> Unit, modifier: Modifier) {
    TextField(value = email , onValueChange = { onTextChanged(it)},modifier=modifier )
}

@Composable
fun ImageLogo() {
    Image(painter = painterResource(id = R.drawable.insta), contentDescription ="logo" )
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
