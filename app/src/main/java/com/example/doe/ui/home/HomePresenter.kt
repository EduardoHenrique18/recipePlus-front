package com.example.doe.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import com.example.doe.MyApplication
import com.example.doe.data.local.local.SharedPreferenceUserDataSource
import com.example.doe.domain.UseCase
import com.example.doe.domain.usecase.DeleteRecipeDetail
import com.example.doe.domain.usecase.SearchRecipeDetail
import com.example.doe.remote.response.CreateRecipeDetailResponse
import com.example.doe.remote.response.DeleteRecipeDetailResponse


class HomePresenter(
    private val view: HomeContract.HomeView,
    private val SearchRecipeUsecase: UseCase<SearchRecipeDetail, List<CreateRecipeDetailResponse>>,
    private val DeleteRecipeUseCase: UseCase<DeleteRecipeDetail, DeleteRecipeDetailResponse>,
    private val userDataSource: SharedPreferenceUserDataSource
) : HomeContract.HomePresenter {

    override fun searchRecipe() {
        var token = userDataSource.getToken()
        token = "Bearer $token"

        SearchRecipeUsecase.run(
            SearchRecipeDetail(
                token
            ),
            callback = { response ->
                if (response.isNotEmpty()) {

                    MyApplication.recipeDb?.recipeDao()?.insertAll(response)
                }
            })
    }

    override fun onCreateRecipeClicked() {
        view.openRegisterRecipe()
    }

    override fun onLogoutClicked() {
        view.logout()
    }

    override fun recipeListObserve(): LiveData<List<CreateRecipeDetailResponse>>? {
        return MyApplication.recipeDb?.recipeDao()?.getAll()
    }

    override fun onClickedItem(recipeInfo: CreateRecipeDetailResponse) {
        view.openRecipe(recipeInfo)
    }

    override fun onClickedDeleteItemOption(recipeInfo: CreateRecipeDetailResponse) {
        deleteRecipe(recipeInfo.recipeId)
        MyApplication.recipeDb?.recipeDao()?.delete(recipeInfo)
    }

    override fun onClickedEditItemOption(recipeInfo: CreateRecipeDetailResponse) {

    }

    private fun deleteRecipe(recipeId: String) {
        var token = userDataSource.getToken()
        token = "Bearer $token"

        DeleteRecipeUseCase.run(
            DeleteRecipeDetail(
                token,
                recipeId
            ),
            callback = { response ->

            })
    }

}