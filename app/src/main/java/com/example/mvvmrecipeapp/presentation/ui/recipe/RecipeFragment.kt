package com.example.mvvmrecipeapp.presentation.ui.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.platform.AndroidUiDispatcher.Companion.Main
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import com.example.mvvmrecipeapp.presentation.ui.recipe_list.RecipeListViewModel
import com.example.mvvmrecipeapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [RecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class RecipeFragment : Fragment() {

    //Using AndroidEntryPoint gives us room to instantiate viewmodel like  private val viewModel: RecipeViewModel by viewModels()
    private val viewModel: RecipeViewModel by viewModels()


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

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = recipe?.let { recipe -> "Selected recipeId: ${recipe.title}" }
                            ?: "LOADING...",
                        style = TextStyle(
                            fontSize = 21.sp
                        )
                    )
                }
            }
        }
//        return ComposeView(requireContext()).apply {
//            val loading = viewModel.loading.value
//            val recipe = viewModel.recipe.value
//
//            setContent {
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Text(
//                        text = recipe?.let {
//                            "SELECTED RECIPE ID: ${recipe.id}  ${recipe.title}"
//                        } ?: "Loading...",
//                        style = TextStyle(
//                            fontSize = 21.sp
//                        )
//                    )
//                }
//            }
//        }
    }
}