package com.leondev7.marveltest.core.mvi

sealed class NavigationEvent {
    object To : NavigationEvent()
    object Back : NavigationEvent()
}