package com.festum.nearbybusiness.user.ui.DependencyInjecation.Repo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserLoginViewModel @Inject constructor(userRepo: UserRepo) : ViewModel() {
    val data = userRepo.getUserLogin()
}