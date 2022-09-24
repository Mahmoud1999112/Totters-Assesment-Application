package com.example.marvelassessmentapplication.ui.charactersList

import com.example.marvelassessmentapplication.domain.model.CharacterModel


data class AppListState(
    val isLoading : Boolean = false,
    val charactersList : List<CharacterModel> = emptyList(),
    val error : String = ""
)