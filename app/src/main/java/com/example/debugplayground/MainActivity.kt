//main

package com.example.debugplayground

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.debugplayground.ui.theme.DebugButtonColors
import com.example.debugplayground.ui.theme.DebugPlaygroundTheme
import com.example.debugplayground.ui.theme.ErrorButtonColors
import com.example.debugplayground.ui.theme.InfoButtonColors
import com.example.debugplayground.ui.theme.WarningButtonColors
import java.lang.Exception
import java.lang.RuntimeException
const val TAG = "DebugPlayground"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DebugPlaygroundTheme {
                App()
            }
        }
    }
}

@Composable
private fun App(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Column (
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Greeting("Playground")

            ActionButton(
                text = "Debug",
                buttonColors = DebugButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ){
                Log.d(TAG, "Clicou em Debug")
            }
            ActionButton(
                text = "Info",
                buttonColors = InfoButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ){
                Log.i(TAG, "Clicou em Info!")
            }
            ActionButton(
                text = "Warning",
                buttonColors = WarningButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ){
                Log.w(TAG, "Clicou em Warning!",)
            }
            ActionButton(
                text = "Error",
                buttonColors = ErrorButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ){
                try{
                    throw RuntimeException("Clicou em ERROR")
                }catch (ex : Exception){
                    Log.e(TAG, "${ex.message}",)
                }
            }
        }
    }


}

@Composable
fun ActionButton(
    text:String,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    modifier: Modifier = Modifier,
    block: () -> Unit,
){
    ElevatedButton(
        onClick = block,
        shape = RoundedCornerShape(5.dp),
        colors = buttonColors,
        modifier = modifier
    ){
        Text(text = text)
    }
}


@Preview(showBackground = true , widthDp = 120)
@Composable
fun ActionButtonPreview() {
    ActionButton(
        text = "Action"){

    }
}

@Preview(showBackground = true , widthDp = 320 , heightDp = 480)
@Composable
fun AppPreview(){
    DebugPlaygroundTheme {
        App()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier){
    Text(
        text = "Welcome to $name!",
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        color = MaterialTheme.colorScheme.secondary
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview(){
    DebugPlaygroundTheme {
        Greeting("Android")
    }

}
