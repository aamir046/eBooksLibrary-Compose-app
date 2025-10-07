package com.aamir.compose.eBooksLibrary.presentation.userprofile.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.domain.interactor.book.FavoriteUseCases
import com.aamir.compose.eBooksLibrary.domain.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouritesViewModel(
    private val favoriteUseCases: FavoriteUseCases
) : ViewModel() {

    private var removeFavouritesJob: Job? = null
    private var getFavouritesJob: Job? = null

    private val _uiState = MutableStateFlow(FavouritesScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        getFavouriteBooks()
    }

    fun onAction(actions: FavouritesScreenActions) {
        when (actions) {
            is FavouritesScreenActions.OnFavouritesIconClick -> {
                removeFromFavourites(actions.book)
            }

            else -> {}
        }
    }

    private fun getFavouriteBooks() {
        getFavouritesJob?.cancel()
        getFavouritesJob = viewModelScope.launch {
            favoriteUseCases.getFavoriteBooksUseCase()
                .flowOn(Dispatchers.IO)
                .collect { books ->
                    _uiState.update {
                        it.copy(favouriteBooks = books)
                    }
                }
        }
    }

    private fun removeFromFavourites(book: Book) {
        removeFavouritesJob?.cancel()
        removeFavouritesJob = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                favoriteUseCases.deleteFavouriteBookUseCase(book)
            }
        }
    }

}