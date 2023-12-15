package com.example.mvvmrecipeapp.presentation.ui.recipe_list


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.mvvmrecipeapp.R
import com.example.mvvmrecipeapp.presentation.components.RecipeCard
import com.example.mvvmrecipeapp.util.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipeListFragment : Fragment() {

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
                val query = remember {
                    mutableStateOf("beef")
                }
                Column() {
                    TextField(
                        value = query.value,
                        onValueChange = { newValue ->
                            query.value = newValue
                        }
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                    LazyColumn() {
                        itemsIndexed(
                            items = recipes
                        ) { index, item ->
                            RecipeCard(recipes = item, onClick = {})
                        }
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
//        return view
    }


}