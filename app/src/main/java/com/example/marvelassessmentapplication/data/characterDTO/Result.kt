package com.example.marvelassessmentapplication.data.characterDTO

import com.example.marvelassessmentapplication.domain.model.CharacterModel

data class Result(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
) {
    fun toCharacter(): CharacterModel {
        return CharacterModel(
            id = id,
            name = name,
            description = description,
            thumbnail = thumbnail.path,
            thumbnailExt = thumbnail.extension,
            comics = comics.items.map {
                it.name
            },
            series = series.items.map {
                it.name
            },
            events = events.items.map {
                it.name
            },
            stories = stories.items.map {
                it.name
            }
        )
    }
}