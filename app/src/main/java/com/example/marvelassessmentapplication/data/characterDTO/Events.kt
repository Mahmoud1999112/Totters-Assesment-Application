package com.example.marvelassessmentapplication.data.characterDTO


data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)