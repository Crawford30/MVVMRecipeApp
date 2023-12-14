package com.example.mvvmrecipeapp.repository

import com.example.mvvmrecipeapp.domain.model.Recipe
import com.example.mvvmrecipeapp.network.RecipeRetrofitService
import com.example.mvvmrecipeapp.network.model.RecipeNetworkDTO
import com.example.mvvmrecipeapp.network.model.RecipeNetworkDTOMapper
import com.example.mvvmrecipeapp.util.DomainMapper
import javax.inject.Inject
import javax.inject.Singleton

/**
 *The RecipeRepositoryIml extends [RecipeRepository] and implements the functions
 * We should pass the [RecipeRetrofitService] as Constructor argement,
 * class RecipeRespositoryImpl(RecipeRetrofitService passed as argument here
 *  private val mapper: DomainMapper<RecipeNetworkDTO, Recipe> or use  private val mapper: RecipeDTOMapper) : RecipeRepository {
override suspend fun search(token: String, page: Int, query: Int): List<Recipe> {
TODO("Not yet implemented")
}

override suspend fun getRecipe(token: String, Id: Int): Recipe {
TODO("Not yet implemented")
}
}
 */

class RecipeRespositoryImpl (

    private val recipeRetrofitService: RecipeRetrofitService,
    private val mapper: RecipeNetworkDTOMapper
) : RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        /**
         * Gets a list of RecipeNetworkDTO
         */
        val result = recipeRetrofitService.search(token, page, query).recipes

        return mapper.toDomainList(result)
    }

    override suspend fun getRecipe(token: String, Id: Int): Recipe {
        return mapper.mapToDomainModel(recipeRetrofitService.get(token, Id))
    }


}

//Method 2 which is used with RepositoryModule
//https://youtu.be/gaa8KcyJqCU?list=PLgCYzUzKIBE_I0_tU5TvkfQpnmrP_9XV8

//@Singleton
//class RecipeRespositoryImpl @Inject constructor(
//    private val recipeRetrofitService: RecipeRetrofitService,
//    private val mapper: RecipeNetworkDTOMapper
//) : RecipeRepository {
//    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
//        /**
//         * Gets a list of RecipeNetworkDTO
//         */
//        val result = recipeRetrofitService.search(token, page, query).recipes
//
//        return mapper.toDomainList(result)
//    }
//
//    override suspend fun getRecipe(token: String, Id: Int): Recipe {
//        return mapper.mapToDomainModel(recipeRetrofitService.get(token, Id))
//    }
//
//
//}