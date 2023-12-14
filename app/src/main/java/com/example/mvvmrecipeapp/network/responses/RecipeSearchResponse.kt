package com.example.mvvmrecipeapp.network.responses

import com.example.mvvmrecipeapp.network.model.RecipeNetworkEntity
import com.google.gson.annotations.SerializedName

/**
 * Business model.
 * This is the response of the search request from the network
 */
class RecipeSearchResponse(

    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeNetworkEntity>,
)