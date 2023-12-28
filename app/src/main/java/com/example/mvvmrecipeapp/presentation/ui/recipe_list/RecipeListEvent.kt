package com.example.mvvmrecipeapp.presentation.ui.recipe_list

sealed class RecipeListEvent {
    //We write events that can take place in an event
    //  object  NewSearchEvent : RecipeListEvent() if we were passing parameter to NewSearch(), we would declare it as
    //  class  NewSearchEvent(val search: String) : RecipeListEvent() instead of object

    object NewSearchEvent : RecipeListEvent()

    object NextPageEvent : RecipeListEvent()
}