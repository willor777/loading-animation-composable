package com.willor.compose_loading_animation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.willor.compose_loading_anim.LoadingAnimation
import com.willor.compose_loading_anim.loadLottieFile
import com.willor.compose_loading_animation.ui.theme.ComposeloadinganimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val l = resources.openRawResource(R.raw.lottie_anim_cube)

        val lr = resources

        val lottieJson = loadLottieFile(resources, R.raw.lottie_anim_cube)

        setContent {
            ComposeloadinganimationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AnimationTest(lottieJson)
                }
            }
        }
    }
}


@Composable
fun AnimationTest(lottieJson: String) {

    var counter = 0

    Column(modifier = Modifier.fillMaxSize()) {

        LoadingAnimation(
            modifier = Modifier.fillMaxSize(),
            lottieJson = lottieJson,
            maxLoopTime = 20000,
            onMaxTime = {
                Log.d("TESTING", "Max time callback called hit")
            },
            onConditionTrue = {
                Log.d("TESTING", "Condition True callback hit")
            },
            condition = {
                counter += 1
                counter > 10
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeloadinganimationTheme {
        AnimationTest("Android")
    }
}