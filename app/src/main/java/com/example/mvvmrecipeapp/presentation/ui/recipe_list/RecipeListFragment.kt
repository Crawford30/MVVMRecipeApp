package com.example.mvvmrecipeapp.presentation.ui.recipe_list


import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.mvvmrecipeapp.R
import com.example.mvvmrecipeapp.presentation.BaseApplication
import com.example.mvvmrecipeapp.presentation.components.*
import com.example.mvvmrecipeapp.ui.theme.MVVMRecipeAppTheme
import com.example.mvvmrecipeapp.util.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale.Category
import javax.inject.Inject


@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        viewModel.recipes.observe(viewLifecycleOwner, { recipes ->
//            //we set the value to recycle view here
//
//        })


        return ComposeView(requireContext()).apply {

            setContent {

                MVVMRecipeAppTheme(
                    darkTheme = application.isDarkTheme.value
                ) {

                    val keyboardController = LocalFocusManager.current
                    val scrollState = rememberScrollState()
                    val coroutineScope = rememberCoroutineScope()

                    /**
                     *The fragment change a bit since we're using MutableState
                     * We get the value inside composables
                     */
                    val recipes =
                        viewModel.recipes.value //anytime the list, the value changes and any composable using this value will also change

                    /**
                     *mutable data structure to get value from the input field
                     * remember mentioned in JS doc, it uses the remember to store an instance of the object in memory
                     * remember can store both mutable or immutable object
                     * In fragment its private val query = ""
                     */
//                val query = remember {
//                    mutableStateOf("beef")
//                }

                    //Mutable state for search query
                    val query = viewModel.query.value

                    //Mutable state for selected category
                    val selectedCategory = viewModel.selectedCategory.value

                    //loading state
                    val isLoading = viewModel.loading.value

                    /**
                     * Or we can use the [savedInstanceState] from JC'
                     * val _query = savedInstanceState{ "beef" }
                     */

                    Column {
                        //Call the AppBar Search
                        SearchAppBar(
                            query = query,
                            onQueryChanged = viewModel::onQueryChanged,
                            onExecuteSearch = viewModel::newSearch,
                            scrollPosition = viewModel.categoryScrollPosition,
                            selectedCategory = selectedCategory,
                            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                            onChangeCategoryPosition = viewModel::onChangeCategoryPosition,
                            onToggleTheme = {
                                application.toggleTheme()

                            }

                        )

//                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//                        repeat(5) {
//                            ShimmerRecipeCardItem(
//                                colors = listOf(
//                                    Color.LightGray.copy(alpha = 0.9f),
//                                    Color.LightGray.copy(alpha = 0.2f),
//                                    Color.LightGray.copy(alpha = 0.9f),
//                                ),
//                                cardHeight = 250.dp,
//                                xshimmer = 0f,
//                                yshimmer = 2000f,
//                                padding = 16.dp
//                            )
//                        }
//
//                    }


//                    ShimmerRecipeCardItem(
//                        colors = listOf(
//                            Color.LightGray.copy(alpha = 0.9f),
//                            Color.LightGray.copy(alpha = 0.2f),
//                            Color.LightGray.copy(alpha = 0.9f)
//                        ), cardHeight = 250.dp
//                    )

                        //All its children will get overlayed over each other
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colors.background),
                        ) {
//                        if (isLoading) {
//                            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//                                repeat(5) {
//                                    ShimmerRecipeCardItem(
//                                        colors = listOf(
//                                            Color.LightGray.copy(alpha = 0.9f),
//                                            Color.LightGray.copy(alpha = 0.2f),
//                                            Color.LightGray.copy(alpha = 0.9f),
//                                        ),
//                                        cardHeight = 250.dp,
//                                        xshimmer = 0f,
//                                        yshimmer = 2000f,
//                                        padding = 16.dp
//                                    )
//                                }
//
//                            }
//
//
////                            ShimmerRecipeCardItem(
////                                colors = listOf(
////                                    Color.LightGray.copy(alpha = 0.9f),
////                                    Color.LightGray.copy(alpha = 0.2f),
////                                    Color.LightGray.copy(alpha = 0.9f),
////                                ),
////                                cardHeight = 250.dp,
////                                xshimmer = 0f,
////                                yshimmer = 2000f,
////                                padding = 16.dp
////                            )
//
//                        } else {
//                            LazyColumn() {
//                                itemsIndexed(
//                                    items = recipes
//                                ) { index, item ->
//                                    RecipeCard(recipes = item, onClick = {})
//                                }
//                            }
//                        }


                            AnimatedShimmerCardItem(
                                isLoading = isLoading,
                                listCount = 10,
                                contentAfterLoading = {
                                    LazyColumn() {
                                        itemsIndexed(
                                            items = recipes
                                        ) { index, item ->
                                            RecipeCard(recipes = item, onClick = {})
                                        }
                                    }
                                },
                                padding = 16.dp,
                                cardHeight = 250.dp
                            )


                            CircularIndeterminateProgressBar(isDisplayed = isLoading, 0.2f)

                        }


                    }


                }

            }
//        val view = inflater.inflate(
//            R.layout.fragment_recipe_list, container, false
//        )
//        view.findViewById<ComposeView>(R.id.compose_view).setContent {
//            Column(
//                modifier = Modifier
//                    .border(border = BorderStroke(1.dp, Color.Black))
//                    .padding(16.dp)
//            ) {
//
//                Text("THIS IS A COMPOSABLE INSIDE THE FRAGMENT XML")
//                Spacer(modifier = Modifier.padding(10.dp))
//                CircularProgressIndicator()
//                Spacer(modifier = Modifier.padding(10.dp))
//                Text("NEAT")
//                Spacer(modifier = Modifier.padding(10.dp))
//
//                val customView = HorizontalDottedProgress(LocalContext.current)
//                AndroidView({customView})
//            }
//        }
//
//        return view}

        }

    }


}


@Composable
fun GradientDemo() {
    val colors = listOf(
        Color.Blue,
        Color.Red,
        Color.Blue
    )

    val brush = linearGradient(
        colors,
        start = Offset(200f, 200f),
        end = Offset(400f, 400f)
    )

    Surface(shape = MaterialTheme.shapes.small) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = brush)
        )
    }

}


@Composable
fun LoadingShimmerEffect() {

    //These colors will be used on the brush. The lightest color should be in the middle

    val gradient = listOf(
        Color.LightGray.copy(alpha = 0.9f), //darker grey (90% opacity)
        Color.LightGray.copy(alpha = 0.3f), //lighter grey (30% opacity)
        Color.LightGray.copy(alpha = 0.9f)
    )

    val transition = rememberInfiniteTransition() // animate infinite times

    val translateAnimation = transition.animateFloat( //animate the transition
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000, // duration for the animation
                easing = FastOutLinearInEasing
            )
        )
    )
    val brush = linearGradient(
        colors = gradient,
        start = Offset(200f, 200f),
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )

    Surface(shape = MaterialTheme.shapes.small) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = brush)
        )
    }

}