package com.leondev7.marveltest.features.characters.domain.error

sealed class CharacterError : Throwable() {
    object NotFound : CharacterError()
}