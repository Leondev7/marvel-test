package com.leondev7.marveltest.core.components.base


sealed class ScreenState : ComponentState {
    object Loading : ScreenState()
    class Error(val error : String) : ScreenState()
    object Empty : ScreenState()
}