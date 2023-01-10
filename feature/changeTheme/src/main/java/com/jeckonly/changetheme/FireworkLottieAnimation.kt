package com.jeckonly.changetheme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.*
import com.jeckonly.designsystem.Mdf

@Composable
fun LottieComposable(
    lottieComposition: LottieComposition?,
    lottieAnimationState:  LottieAnimationState,
    modifier: Modifier = Modifier
) {
    LottieAnimation(
        lottieComposition,
        modifier = modifier,
        progress = { lottieAnimationState.progress }
    )
}

@Composable
fun FireWorkLottie(
    modifier: Modifier = Modifier
) {
    var isPlaying by remember {
        mutableStateOf(false)
    }

    val lottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(com.jeckonly.designsystem.R.raw.fireworks),
    )

    val lottieAnimationState = animateLottieCompositionAsState (
        composition = lottieComposition,
        isPlaying = isPlaying,
        speed = 1.8f
    )

    if (lottieAnimationState.progress == 1f) {
        isPlaying = !isPlaying
    }
    Column {
        LottieComposable(
            lottieComposition = lottieComposition,
            lottieAnimationState = lottieAnimationState,
            modifier = modifier
        )
        Button(onClick = {
            if (lottieAnimationState.isPlaying == false) {
                isPlaying = !isPlaying
            }
        }) {
            Text(text = "click")
        }
    }
}



@Preview(showBackground = true, backgroundColor = 0xffffff)
@Composable
fun PreviewFireWorkLottie() {
   FireWorkLottie(modifier = Mdf.size(100.dp))
}


