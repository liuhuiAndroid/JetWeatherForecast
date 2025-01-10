package com.weather.forecast.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.airbnb.lottie.compose.rememberLottieRetrySignal
import com.weather.forecast.R
import timber.log.Timber

@Composable
fun CustomLottieAnimation() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LottieAnimationExample()
        LottieAnimationURLExample()
        LottieAnimationColor()
    }
}

/**
 * A composable function demonstrating the usage of LottieAnimation to display an animated Lottie composition.
 * The Lottie composition is loaded from a raw resource file named "orange_skating_animation".
 */
@Composable
fun LottieAnimationExample() {
    // Obtain the Lottie composition from the specified raw resource file.
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.orange_skating_animation))

    // Animate the Lottie composition with a continuous loop.
    // 使用连续循环
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    // Display the Lottie animation with specified properties.
    LottieAnimation(
        composition = composition,
        progress = { progress },
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(100.dp)
    )
}

/**
 * A composable function demonstrating the usage of LottieAnimation to display an animated Lottie composition
 * loaded from a URL. It also shows how to handle retries in case of loading failures.
 */
@Composable
fun LottieAnimationURLExample() {
    // Create a retry signal to control the retry mechanism.
    val retrySignal = rememberLottieRetrySignal()

    // Load the Lottie composition from the specified URL with retry handling.
    val composition by rememberLottieComposition(
        LottieCompositionSpec.Url("not a url"), // Replace with the actual URL
        onRetry = { failCount, exception ->
            // Log information about the retry attempt and exception.
            Timber.tag("FailCount: ").d(failCount.toString())
            Timber.tag("Exception: ").d(exception.stackTraceToString())

            // Request a retry using the retry signal.
            retrySignal.awaitRetry()

            // Continue retrying. Return false to stop trying.
            true
        }
    )

    // Animate the Lottie composition with a continuous loop.
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    // Display the Lottie animation with specified properties.
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}


/**
 * A private composable function demonstrating the usage of LottieAnimation with dynamic properties
 * to change the color and apply a blur effect to specific parts of the Lottie animation.
 */
@Composable
private fun LottieAnimationColor() {
    // Load the Lottie composition from the specified raw resource.
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.heart))

    // Define a list of colors to be used for dynamic color changes.
    val colors = remember {
        listOf(
            Color.Red,
            Color.Green,
            Color.Blue,
            Color.Yellow,
        )
    }

    // Track the index of the current color in the list.
    var colorIndex by remember { mutableIntStateOf(0) }

    // Retrieve the current color based on the index.
    val color by remember { derivedStateOf { colors[colorIndex] } }

    // Convert dp to pixels for blur effect.
    val blurRadius = with(LocalDensity.current) { 12.dp.toPx() }

    // Define dynamic properties for color and blur effect.
    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = color.toArgb(),
            keyPath = arrayOf(
                "H2",
                "Shape 1",
                "Fill 1",
            )
        ),
        rememberLottieDynamicProperty(
            property = LottieProperty.BLUR_RADIUS,
            value = blurRadius,
            keyPath = arrayOf(
                "**",
                "Stroke 1",
            )
        ),
    )

    // Display the Lottie animation with specified properties and interaction.
    LottieAnimation(
        composition,
        iterations = LottieConstants.IterateForever,
        dynamicProperties = dynamicProperties,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { colorIndex = (colorIndex + 1) % colors.size },
            )
    )
}
