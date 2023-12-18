package com.example.mvvmrecipeapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean) {
    if (isDisplayed) {
        //Using Constraint Layout
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            //Giving an id in compose
            val (progressBar, text) = createRefs() //creates an id

            val topGuideLine = createGuidelineFromTop(0.3f) //creates a guideline 30% from the to

            CircularProgressIndicator(
                modifier = Modifier.constrainAs(progressBar) {
                    //Give access to top, bottom, etc
                    top.linkTo(topGuideLine)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                color = MaterialTheme.colors.primary
            )

            Text(
                text = "Loading...",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp
                ),
                modifier = Modifier.constrainAs(text) {
                    top.linkTo(progressBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }
            )

        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(50.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//             CircularProgressIndicator(
//                color = MaterialTheme.colors.primary
//            )
//        }
    }
}