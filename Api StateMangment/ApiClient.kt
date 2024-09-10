package com.festum.nearbybusiness.user.ui.DependencyInjecation

import com.festum.nearbybusiness.user.ui.DependencyInjecation.ResponseModel.UserLogin
import retrofit2.Response
import retrofit2.http.POST

interface ApiClient {
    @POST("user/login")
    suspend fun setupLogin(): Response<UserLogin>
}