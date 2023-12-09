package com.example.mvvmrecipeapp.network.model

import com.example.mvvmrecipeapp.domain.model.Recipe
import com.example.mvvmrecipeapp.util.EntityMapper

/**
 * Maps the network data for easy conversion between the models
 * It implements the [EntityMapper]
 * https://youtu.be/yUk4NzkdmG8?list=PLgCYzUzKIBE_I0_tU5TvkfQpnmrP_9XV8&t=805
 */
class RecipeNetworkMapper : EntityMapper<RecipeNetworkEntity, Recipe> {

    /**
     * Used when getting data from the network
     */
    override fun mapFromEntity(entity: RecipeNetworkEntity): Recipe {
        return Recipe(
            id = entity.pk,
            title = entity.title,
            featuredImage = entity.featuredImage,
            rating = entity.rating,
            publisher = entity.publisher,
            sourceUrl = entity.sourceUrl,
            description = entity.description,
            cookingInstructions = entity.cookingInstructions,
            ingredients = entity.ingredients?: listOf(),
            dateAdded = entity.dateAdded,
            dateUpdated = entity.dateUpdated,
        )
    }

    /**
     * Used when publishing data to the network
     */
    override fun mapToEntity(domainModel: Recipe): RecipeNetworkEntity {
        return RecipeNetworkEntity(
            pk = domainModel.id,
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating,
            publisher = domainModel.publisher,
            sourceUrl = domainModel.sourceUrl,
            description = domainModel.description,
            cookingInstructions = domainModel.cookingInstructions,
            ingredients = domainModel.ingredients,
            dateAdded = domainModel.dateAdded,
            dateUpdated = domainModel.dateUpdated,
        )
    }

    /**
     * Converts a list of RecipeNetworkEntity to a list of Recipe
     */
    fun fromEntityList(initial: List<RecipeNetworkEntity>): List<Recipe>{
        return initial.map { mapFromEntity(it) }
    }

    /**
     * Converts a list of Recipe to a list of RecipeNetworkEntity
     */
    fun toEntityList(initial: List<Recipe>): List<RecipeNetworkEntity>{
        return initial.map { mapToEntity(it) }
    }


}