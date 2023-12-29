package com.example.mvvmrecipeapp.presentation.ui.recipe

sealed class RecipeEvent {

    //We used data class so that we can pass argument to it ie id
    data class GetRecipeEvent(
        val Id: Int
    ): RecipeEvent(){

    }

}