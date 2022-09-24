package com.example.marvelassessmentapplication.domain.repository

import com.example.marvelassessmentapplication.data.characterDTO.CharacterDTO
import com.example.marvelassessmentapplication.data.charactersDTO.CharactersDTO

interface MarvelRepository {

    suspend fun getAllCharacters(offset:Int): CharactersDTO
    suspend fun getAllSearchedCharacters(search:String):CharactersDTO
    suspend fun getCharacterById(id:String): CharacterDTO
}