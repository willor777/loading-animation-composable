package com.willor.compose_loading_anim

import android.content.res.Resources
import androidx.annotation.RawRes
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive


@Composable
fun LoadingAnimation(
    modifier: Modifier,
    lottieJson: String,
    onMaxTime: () -> Unit,
    onConditionTrue: () -> Unit,
    condition: () -> Boolean,
    speed: Float = 1f,
    maxLoopTime: Long = 10000,
    ){

    val showAnimation = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit){
        val startTime = System.currentTimeMillis()

        while(this.isActive){

            // Check for timeout
            if (System.currentTimeMillis() - startTime > maxLoopTime){
                showAnimation.value = false
                onMaxTime()
                break
            }
            
            // Check if condition is has been met
            if (condition()){
                showAnimation.value = false
                onConditionTrue()
                break
            }
            
            // Sleep the loop for half a second
            delay(500)
        }
    }
    
    Animation(
        modifier = modifier,
        show = showAnimation.value,
        speed = speed,
        lottieJson = lottieJson
    )
}


@Composable
fun Animation(
    modifier: Modifier,
    show: Boolean,
    speed: Float,
    lottieJson: String,
){

    // Load the json as a lottie composition
    val composition by rememberLottieComposition(
        LottieCompositionSpec.JsonString(lottieJson)
    )

    // Set up the progress tracking
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = speed,
    )

    if (show){
        LottieAnimation(
            modifier = modifier,
            composition = composition,
            progress = { progress }
        )
    }
}



fun loadLottieFile(resources: Resources, @RawRes id: Int): String{
    return resources.openRawResource(id).bufferedReader().use{
        it.readText()
    }
}