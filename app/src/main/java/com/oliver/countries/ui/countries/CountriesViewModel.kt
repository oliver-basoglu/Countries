package com.oliver.countries.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliver.countries.data.model.CountriesResponseResult
import com.oliver.countries.data.repositories.CountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private var repository: CountriesRepository
) : ViewModel() {

    private var _countriesResponseResultLiveData = MutableLiveData<CountriesResponseResult>()
    val countriesResponseResultLiveData: LiveData<CountriesResponseResult>
        get() = _countriesResponseResultLiveData

    fun fetchCountriesData() {
        Timber.d("fetching data")
        viewModelScope.launch {
            _countriesResponseResultLiveData.value = CountriesResponseResult.IsLoading
            viewModelScope.launch {
                repository.fetchCountriesData().collect {
                    _countriesResponseResultLiveData.value = it
                }
            }
        }
    }
}