package com.leondev7.marveltest.features.characters.presentation.list


/**
 * Different states for [CharacterListViewModel].
 */
sealed class CharacterListScreenState {

    /**
     * Loading
     */
    object Loading : CharacterListScreenState()

    /**
     * Empty.
     */
    object Empty : CharacterListScreenState()

    /**
     * Login error.
     */
    object Error : CharacterListScreenState()

    /**
     * Success
     */
    object Success : CharacterListScreenState()

    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================

    /**
     * Check if current view state is [Loading].
     *
     * @return True if is loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if current view state is [Empty].
     *
     * @return True if is empty state, otherwise false.
     */
    fun isEmpty() = this is Empty

    /**
     * Check if current view state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error

    /**
     * Check if current view state is [Success].
     *
     * @return True if is success state, otherwise false.
     */
    fun isSuccess() = this is Success


}