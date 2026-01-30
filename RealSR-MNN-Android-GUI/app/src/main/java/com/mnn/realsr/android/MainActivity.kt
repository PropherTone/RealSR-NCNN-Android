package com.mnn.realsr.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mnn.realsr.MnnSrNative
import com.mnn.realsr.android.ui.theme.RealSRMNNAndroidGUITheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RealSRMNNAndroidGUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LaunchedEffect(Unit) {
                        launch(Dispatchers.IO) {
                            val re = MnnSrNative().runDefault(
                                inputPath = "/sdcard/DCIM/upscale/300.jpeg",
                                outputPath = "/sdcard/DCIM/test.png",
                                modelPath = "/sdcard/Download/llvm/ESRGAN-Nomos8kSC-x4.mnn"
                            )
                            Log.i("TAG", "onCreate: $re")
                        }
                    }
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RealSRMNNAndroidGUITheme {
        Greeting("Android")
    }
}