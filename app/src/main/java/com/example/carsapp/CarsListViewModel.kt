package com.example.carsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** View model layer for the cars feature. */
internal class CarsListViewModel(
    private val useCase: CarsListUseCase = CarsListUseCase()
) : ViewModel() {

    // In the future, use flow instead of live data
    private val mutableViewState = MutableLiveData<CarsListState>()

    /** Current cars list state. */
    val viewState: LiveData<CarsListState> = mutableViewState

    init {
        viewModelScope.launch {
            getCars()
        }
    }

    suspend fun getCars() {
        // Remove delay when call some API
        delay(2000)
        mutableViewState.postValue(CarsListState.Loading)

        return try {
            val cars = useCase.getCars()

            when (cars.isEmpty()) {
                true -> mutableViewState.postValue(CarsListState.Empty)
                false -> mutableViewState.postValue(CarsListState.Loaded(cars))
            }

        } catch (error: Throwable) {
            mutableViewState.postValue(CarsListState.Error)
        }
    }
}
