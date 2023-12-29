package com.example.mvvmrecipeapp.presentation.components

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvvmrecipeapp.R
import com.example.mvvmrecipeapp.domain.model.Recipe
import com.example.mvvmrecipeapp.presentation.ui.recipe_list.PAGE_SIZE
import com.example.mvvmrecipeapp.presentation.ui.recipe_list.RecipeListEvent
import com.example.mvvmrecipeapp.util.Constants.TAG
import com.example.mvvmrecipeapp.util.SnackbarController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch


@Composable
fun RecipeList(
    isLoading: Boolean,
    recipes: List<Recipe>,
    page: Int,
    scaffoldState: ScaffoldState,
    onChangeRecipeScrollPosition: (Int) -> Unit,
    onNextPage: (RecipeListEvent) -> Unit,
    snackbarController: SnackbarController,
    navController: NavController

) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background),
    ) {
        AnimatedShimmerCardItem(
            isLoading = isLoading && recipes.isEmpty(),
            listCount = 10,
            contentAfterLoading = {
                LazyColumn() {
                    itemsIndexed(
                        items = recipes
                    ) { index, item ->
                        onChangeRecipeScrollPosition(index) //keep track of scroll position

                        //check if we're at the bottom of the list
                        if ((index + 1) > (page * PAGE_SIZE) && !isLoading) {
//                                                viewModel.nextPage()
                            onNextPage(RecipeListEvent.NextPageEvent)
                        }
                        RecipeCard(
                            recipes = item,
                            onClick = {
                                if (item.id != null) {
                                    //Go to another fragment
//                                    val bundle = Bundle()
//                                    bundle.putInt("recipeId", item.id)
//
//                                    Log.d(TAG, "Card Tapped: ${index}")
//                                    print("Card Tapped: ${index}")
//                                    //Do the navigation
//                                    navController.navigate(R.id.viewRecipe, bundle)

                                    val bundle = Bundle()
                                    bundle.putInt("recipeId", item.id)
                                    navController.navigate(R.id.viewRecipe, bundle)





                                } else {
                                    //This part is for demo ONLY
                                    snackbarController.getScope().launch {
                                        snackbarController.showSnackbar(
                                            scaffoldState = scaffoldState,
                                            message = "RECIPE ERROR",
                                            actionLabel = "OK"
                                        )
                                    }
                                }

                            }
                        )
                    }
                }
            },
            padding = 16.dp,
            cardHeight = 250.dp
        )

        //===Below has been moved to app theme====
//        CircularIndeterminateProgressBar(isDisplayed = isLoading, 0.2f)
//
//        DefaultSnackbar(
//            snackbarHostState = scaffoldState.snackbarHostState,
//            onDismiss = {
//                scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
//            },
//            modifier = Modifier.align((Alignment.BottomCenter))
//        )

    }

}