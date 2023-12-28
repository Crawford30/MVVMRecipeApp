package com.example.mvvmrecipeapp.presentation.ui.recipe

import android.os.Bundle
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [RecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class RecipeFragment : Fragment() {
    private var recipeId: MutableState<Int>? = mutableStateOf(-1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Main).launch {
            delay(1000)
            arguments?.getInt("recipeId")?.let { Id ->
                recipeId?.value = Id
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        if (recipeId?.value != -1) {
                            "Selected Recipe ID: ${recipeId?.value}"

                        } else {
                            "Loading..."
                        },
                        style = TextStyle(
                            fontSize = 26.sp
                        )
                    )
                }

            }
        }

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }


}