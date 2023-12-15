package com.example.mvvmrecipeapp.presentation.ui.recipe_list


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
    private val _recipeList: MutableLiveData<List<Recipe>> = MutableLiveData() //The value is private and the UI cant see it



    /**
     * For UI to see it, we create another variable(Its a readonly value)
     * We then observe this value in the activity or fragment
     */
    val recipes: LiveData<List<Recipe>> get() = _recipeList

    /**
     * Get the data
     */
    init {

        viewModelScope.launch {
         val result =    repository.search(
                token = token,
                page = 1,
                query = "chicken"
            )

            /**
             * Set the value
             */
            _recipeList.value =  result
        }
    }


    fun getRepo() = repository

    fun getToken() = token




}