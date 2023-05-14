package com.example.practice_3.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice_3.data.repository.MoviesRepositoryImpl
import com.example.practice_3.data.repository.TAG
import com.example.practice_3.domain.model.MoviesDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryImpl: MoviesRepositoryImpl,
) : ViewModel() {

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    private val _stateMovies =
        MutableStateFlow<List<MoviesDomain>>(emptyList())
    val stateMovies = _stateMovies.asStateFlow()

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val list = repositoryImpl.refreshMovies()
            _stateMovies.value = list
        }
    }

    fun removeAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.removeAll()
        }
    }
}