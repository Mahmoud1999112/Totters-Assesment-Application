package com.example.marvelassessmentapplication.ui.character

import com.example.marvelassessmentapplication.domain.model.CharacterModel


data class CharacterState(
    val isLoading : Boolean = false,
    val characterDetail : List<CharacterModel> = emptyList(),
    val error : String = ""
)