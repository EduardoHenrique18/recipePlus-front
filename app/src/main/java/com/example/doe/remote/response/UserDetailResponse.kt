package com.example.doe.remote.response

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    @SerializedName("userId") val userId: String,
    @SerializedName("userName") val name: String
)