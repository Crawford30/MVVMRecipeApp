package com.example.mvvmrecipeapp.presentation.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide.init
import kotlinx.coroutines.delay


@Composable
fun ShimmerRecipeCardItem(
    colors: List<Color>,
    cardHeight: Dp,
    xshimmer: Float,
    yshimmer: Float,
    padding: Dp
) {

    val transition = rememberInfiniteTransition() // animate infinite times
    val xtranslateAnimation = transition.animateFloat(
        //animate the transition
        initialValue = xshimmer,
        targetValue = yshimmer,

        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1300, // duration for the animation
                delayMillis = 300, //delay
                easing = LinearEasing,
            )
        ),

        )

    val ytranslateAnimation = transition.animateFloat( //animate the transition
        initialValue = xshimmer,
        targetValue = yshimmer,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1300, // duration for the animation
                delayMillis = 300, //delay
                easing = LinearEasing,
            )
        )
    )
    val brush = linearGradient(
        colors = colors,
        start = Offset(10f, 10f),
        end = Offset(
            x = xtranslateAnimation.value,
            y = ytranslateAnimation.value
        )
    )

    Column(modifier = Modifier.padding(padding)) {
        Surface(shape = MaterialTheme.shapes.small) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight)
                    .background(brush = brush)
            )
        }

    }


}

//@Composable
//fun ShimmerRecipeCardItem(
//    colors: List<Color>,
//    cardHeight: Dp
//) {
////    val colors = listOf(
////        Color.Blue,
////        Color.Red,
////        Color.Blue
////    )
//
//    val brush = Brush.linearGradient(
//        colors,
//        start = Offset(200f, 200f),
//        end = Offset(400f, 400f)
//    )
//
//    Surface(shape = MaterialTheme.shapes.small) {
//        Spacer(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(cardHeight)
//                .background(brush = brush)
//        )
//    }
//
//}