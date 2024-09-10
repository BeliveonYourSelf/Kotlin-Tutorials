package com.festum.nearbybusiness.user.ui.DependencyInjecation.Repo

import com.festum.nearbybusiness.user.ui.DependencyInjecation.ApiClient
import com.festum.nearbybusiness.user.ui.DependencyInjecation.Utills.result
import javax.inject.Inject

class UserRepo @Inject constructor(val apiClient: ApiClient) {
    fun getUserLogin() = result {
        apiClient.setupLogin()
    }
}