package com.example.marvelassessmentapplication.ui.charactersList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelassessmentapplication.domain.useCases.CharactersUseCase
import com.example.marvelassessmentapplication.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCase : CharactersUseCase
) : ViewModel(){
    private val marvelValue = MutableStateFlow(AppListState())
    var _marvelValue : StateFlow<AppListState> = marvelValue

    fun getAllCharactersData(offset:Int)=viewModelScope.launch(Dispatchers.IO){
        charactersUseCase(offset).collect {
            when(it){
                is State.Success ->{
                    marvelValue.value = AppListState(charactersList = it.data?: emptyList())
                    Log.d("toCharacter",_marvelValue.value.toString())
                }
                is State.Loading ->{
                    marvelValue.value = AppListState(isLoading = true)
                    Log.d("loading",it.data.toString())
                }
                is State.Error ->{
                    marvelValue.value = AppListState(error = it.message?:"An Unexpected Error")
                    Log.d("Error",it.data.toString())
                }
            }
        }
    }
}