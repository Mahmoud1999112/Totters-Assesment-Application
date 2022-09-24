package com.example.marvelassessmentapplication.domain.model

import com.example.marvelassessmentapplication.data.charactersDTO.Series

data class CharacterModel(
    val id : Int,
    val name : String,
    val description : String,
    val thumbnail : String,
    val thumbnailExt: String,
    val comics : List<String>,
    val series : List<String>,
    val events:List<String>,
    val stories:List<String>,
)