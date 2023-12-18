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
import kotlinx.coroutines.delay
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
    val query: MutableState<String> = mutableStateOf("")

    //Keep track of selectedCategory
    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    //Keeping track of scroll position
    var categoryScrollPosition: Float = 0f

    //Progress bar state
    val loading = mutableStateOf(false)

    /**
     * Get the data
     */
    init {
        newSearch() //newSearch("chicken")
    }

    fun newSearch() {
        loading.value = true //set loading to true


        viewModelScope.launch {
            delay(2000)

            resetSearchState()

            val result = repository.search(
                token = token,
                page = 1,
                query = query.value
            )

            /**
             *Set the value
             */
            recipes.value = result
            //If we get results, we reset the loading state
            loading.value = false
        }

    }

    /**
     *This function is used to change the value of the input field since we can do it directly in the fragment
     */
    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    /**
     *This function is used to change the value of selected Category
     */

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category) //get the enum value
        selectedCategory.value = newCategory

        onQueryChanged(category) //then change the query parameter
    }


    /**
     *This function is used to change the category position
     */
    fun onChangeCategoryPosition(position: Float) {
        categoryScrollPosition = position
    }

    /**
     *This function is used to clear the selected category
     */
    private fun clearSelectedCategory() {
        selectedCategory.value = null
    }

    /**
     *This function is used to reset the search state
     */

    private fun resetSearchState() {
        recipes.value = listOf() //reset recipe list

        //If the selected category is not equal to query.value, we clear it
        if (selectedCategory.value?.value != query.value) {
            clearSelectedCategory()
        }
    }


    fun getRepo() = repository

    fun getToken() = token


}