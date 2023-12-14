package com.example.mvvmrecipeapp.di

import com.example.mvvmrecipeapp.network.RecipeRetrofitService
import com.example.mvvmrecipeapp.network.model.RecipeNetworkDTOMapper
import com.example.mvvmrecipeapp.repository.RecipeRepository
import com.example.mvvmrecipeapp.repository.RecipeRespositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * provideRecipeRepository
     */
    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeRetrofitService: RecipeRetrofitService,
        recipeNetworkDTOMapper: RecipeNetworkDTOMapper
    ): RecipeRepository {
        /**
         * The repository require recipeRetrofitService and recipeNetworkDTOMapper
         * By specifying or returning the interface and its implementation makes testing easy
         * Makes it easy by building the fake and returning fake instance
         */
        return  RecipeRespositoryImpl(recipeRetrofitService, recipeNetworkDTOMapper)
    }


//    /**
//     * provideRecipeRepository and can return a fake Repo
    //https://youtu.be/gaa8KcyJqCU?list=PLgCYzUzKIBE_I0_tU5TvkfQpnmrP_9XV8&t=663
//     */
    //
//    @Singleton
//    @Provides
//    fun provideRecipeRepository(
//        recipeRetrofitService: RecipeRetrofitService,
//        recipeNetworkDTOMapper: RecipeNetworkDTOMapper
//    ): RecipeRepositoryFake {
//        /**
//         * The repository require recipeRetrofitService and recipeNetworkDTOMapper
//         * By specifying or returning the interface and its implementation makes testing easy
//         * Makes it easy by building the fake and returning fake instance
//         */
//        return  RecipeRespositoryImpl(recipeRetrofitService, recipeNetworkDTOMapper)
//    }

}