package com.example.mvvmrecipeapp.presentation.ui.recipe_list


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmrecipeapp.domain.model.Recipe
import com.example.mvvmrecipeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

/**
 * RecipeList viewmodel
 */
@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val repository: RecipeRepository,
    @Named("auth_token") private val token: String
) : ViewModel() {

    /**
     * Create mutable live data to watch  changes and pass data to the UI
     *
     */
//    private val _recipeList: MutableLiveData<List<Recipe>> = MutableLiveData() //The value is private and the UI cant see it

//
    /**
     * Using Compose mutableState
     *
     */

    val recipes: MutableState<List<Recipe>> = mutableStateOf(ArrayList())

//    /**
//     * For UI to see it, we create another variable(Its a readonly value)
//     * We then observe this value in the activity or fragment
//     */
//    val recipes: LiveData<List<Recipe>> get() = _recipeList


    //Since query input lost value on configuration, we need to persist it using the viewmodel
    val query: MutableState<String> = mutableStateOf("chicken")

    /**
     * Get the data
     */
    init {
        newSearch()
    }

    private fun newSearch() {
        viewModelScope.launch {
            val result = repository.search(
                token = token,
                page = 1,
                query = "chicken"
            )

            /**
             *Set the value
             */
            recipes.value = result
        }

    }

    /**
     *This function is used to change the value of the input field since we can do it directly in the fragment
     */
    fun onQueryChanged(query: String) {
        this.query.value = query
    }


    fun getRepo() = repository

    fun getToken() = token


}