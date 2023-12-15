package com.example.mvvmrecipeapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mvvmrecipeapp.R
import com.example.mvvmrecipeapp.domain.model.Recipe

/**
 *Creating the equivalent ViewHolder layout for recycler using composables
 * The function takes in the [Recipe] and [onClick] event to denote what happens
 * when an item in the recycler is clicked
 */

@Composable
fun RecipeCard(
    recipes: Recipe,
    onClick: () -> Unit
) {
    /**
     *CardView equivalent in  JC is [card]
     */
    Card(
        shape = MaterialTheme.shapes.small, //prebuilt theme
        modifier = Modifier
            .padding(
                top = 6.dp,
                bottom = 6.dp,
                end = 6.dp,
                start = 6.dp
            )
            .fillMaxWidth()
            .clickable { (onClick) },
        elevation = 8.dp

    ) {

        Column() {
            /**
             *Recipe Image
             */
            recipes.featuredImage?.let { url ->
                Image(
                    painter = painterResource(id = R.drawable.empty_plate),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(225.dp),
                    contentScale = ContentScale.Crop
                )
            }
            /**
             *Recipe Title
             */
            recipes.title?.let { title ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                ) {
                    /**
                     *Recipe Title Text
                     */
                    Text(
                        text = title,
                        modifier = Modifier
                            .fillMaxWidth(0.85f) //occupy 85% width
                            .wrapContentWidth(Alignment.Start),
                        style = MaterialTheme.typography.h5
                    )

                    /**
                     *Recipe  Rating
                     */

                    Text(
                        text = recipes.rating.toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.h6
                    )
                }
            }

        }
    }

}