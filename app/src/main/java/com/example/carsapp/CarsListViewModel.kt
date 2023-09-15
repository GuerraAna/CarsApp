package com.example.carsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carsapp.model.Car
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 */
internal class CarsListViewModel(
    private val useCase: CarsListUseCase = CarsListUseCase()
) : ViewModel() {

    private val mutableViewState = MutableLiveData<CarsListState>()

    /**
     * Current cars list state.
     */
    val viewState: LiveData<CarsListState> = mutableViewState

    init {
        viewModelScope.launch {
            getCars()
        }
    }

    suspend fun getCars() {
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