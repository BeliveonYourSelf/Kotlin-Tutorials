package com.learn.app.kotlins.di.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.app.kotlins.apputills.ApiStatus
import com.learn.app.kotlins.datamodels.CoinResponse
import com.learn.app.kotlins.di.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {
    private val _cointList = MutableLiveData<ApiStatus<CoinResponse>>()
    val coinList: LiveData<ApiStatus<CoinResponse>>
        get() {
            return _cointList
        }

    fun getCoinList(vs_Currency: String) {
        viewModelScope.launch {
            apiRepository.getCoinList(vs_Currency)
                .catch {
                    _cointList.postValue(ApiStatus.Error(it.message.toString()))
                }
                .collect {
                    _cointList.postValue(ApiStatus.SuccessApiCall(it.data))
                }


        }
    }
}