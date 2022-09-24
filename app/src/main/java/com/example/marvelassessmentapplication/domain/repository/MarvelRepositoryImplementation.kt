package com.example.marvelassessmentapplication.domain.repository

import com.example.marvelassessmentapplication.data.AppApi
import com.example.marvelassessmentapplication.data.characterDTO.CharacterDTO
import com.example.marvelassessmentapplication.data.charactersDTO.CharactersDTO
import com.example.marvelassessmentapplication.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImplementation @Inject constructor(
    private val api : AppApi
) : MarvelRepository {
    override suspend fun getAllCharacters(offset:Int): CharactersDTO {
        return api.getAllCharacters(offset=offset.toString())
    }

    override suspend fun getAllSearchedCharacters(search: String): CharactersDTO {
        return api.getAllSearchedCharacters(search=search)
    }

    override suspend fun getCharacterById(id: String): CharacterDTO {
        return api.getCharacterById(id)
    }
}