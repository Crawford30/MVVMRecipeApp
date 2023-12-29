package com.example.mvvmrecipeapp.presentation.ui.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.platform.AndroidUiDispatcher.Companion.Main
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mvvmrecipeapp.presentation.BaseApplication
import com.example.mvvmrecipeapp.presentation.components.AnimatedRecipeDetailShimmer
import com.example.mvvmrecipeapp.presentation.components.CircularIndeterminateProgressBar
import com.example.mvvmrecipeapp.presentation.components.DefaultSnackbar
import com.example.mvvmrecipeapp.presentation.components.RecipeView
import com.example.mvvmrecipeapp.presentation.ui.recipe_list.RecipeListViewModel
import com.example.mvvmrecipeapp.ui.theme.MVVMRecipeAppTheme
import com.example.mvvmrecipeapp.util.Constants
import com.example.mvvmrecipeapp.util.SnackbarController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [RecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private val IMAGE_HEIGHT = 260

@AndroidEntryPoint
class RecipeFragment : Fragment() {

    //Using AndroidEntryPoint gives us room to instantiate viewmodel like  private val viewModel: RecipeViewModel by viewModels()
    private val viewModel: RecipeViewModel by viewModels()

    @Inject
    lateinit var application: BaseApplication


    private val snackbarController = SnackbarController(lifecycleScope)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getInt("recipeId")?.let { recipeId ->
            viewModel.onTriggerEvent(RecipeEvent.GetRecipeEvent(recipeId))

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val loading = viewModel.loading.value

                val recipe = viewModel.recipe.value
                val scaffoldState = rememberScaffoldState()

                MVVMRecipeAppTheme(darkTheme = application.isDarkTheme.value) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        }
                    ) {

                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {


//                            recipe?.let {
//                                if (it.id == 1) {
//                                    snackbarController.showSnackbar(
//                                        scaffoldState = scaffoldState,
//                                        message = "An error occurred",
//                                        actionLabel = "Ok"
//                                    )
//
//                                }
//                            }

                            AnimatedRecipeDetailShimmer(
                                isLoading = (loading && recipe == null),
                                contentAfterLoading = {
                                    RecipeView(recipe = recipe!!)
                                },
                                padding = 0.dp,
                                cardHeight = IMAGE_HEIGHT.dp
                            )

                            CircularIndeterminateProgressBar(
                                isDisplayed = loading,
                                verticalBias = 0.2f
                            )

                            DefaultSnackbar(
                                snackbarHostState = scaffoldState.snackbarHostState,
                                onDismiss = {
                                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                                },
                                modifier = Modifier.align(Alignment.BottomCenter)
                            )

                        }

                    }

                }


            }
        }

    }
}