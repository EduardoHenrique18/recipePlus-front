package com.example.doe.data.local.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.doe.remote.response.CreateRecipeDetailResponse

@Dao
interface RecipeDao {
    @Query("SELECT * FROM CreateRecipeDetailResponse")
    fun getAll(): LiveData<List<CreateRecipeDetailResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(recipes: List<CreateRecipeDetailResponse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(recipe: CreateRecipeDetailResponse)

    @Delete
    fun delete(recipe: CreateRecipeDetailResponse)

    @Query("UPDATE CreateRecipeDetailResponse SET description = :description, prepareMethod = :prepareMethod, product = :product, recipeYield = :recipeYield WHERE recipeId = :recipeId")
    fun editRecipe(description: String, prepareMethod: String, recipeYield: String, product: String, recipeId: String)
}