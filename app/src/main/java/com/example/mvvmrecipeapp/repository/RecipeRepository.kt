package com.example.mvvmrecipeapp.repository

import com.example.mvvmrecipeapp.domain.model.Recipe

/**
 *The Repository should return the [domainmdel] since its the data being exposed to the UI through [viewmodel]
 * It doesnt care about the DTO classes
 */
interface RecipeRepository {

    /**
     *The search function returns the List of [Recipe] from the domain model(bness model)
     */
    suspend fun search(token: String, page: Int, query: String): List<Recipe>

    /**
     *The search function returns a single object of [Recipe] from the domain model(bness model)
     */
    suspend fun getSingleRecipe(token: String, id: Int): Recipe
}