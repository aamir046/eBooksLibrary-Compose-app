package com.aamir.compose.eBooksLibrary.presentation.bookdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.domain.interactor.book.FavoriteUseCases
import com.aamir.compose.eBooksLibrary.domain.model.Book
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailsViewModel(
    private val favoriteUseCases: FavoriteUseCases
):ViewModel(){
    private val _uiState = MutableStateFlow(BookDetailsScreenState())

    val uiState = _uiState.asStateFlow()

    val onActions: (BookDetailsScreenActions) -> Unit = { action ->
        when (action) {
            is BookDetailsScreenActions.OnToggleFavorite -> {
                if(_uiState.value.isFavourite){
                    removeFromFavourites(action.book)
                }else{
                    addToFavourites(action.book.copy(isFavourite = true))
                }
                _uiState.update {
                    it.copy(
                        isFavourite = !it.isFavourite
                    )
                }
            }
            else -> {}
        }
    }

    fun onSelectBook(selectedBook: Book?) {
            selectedBook?.let {book->
                _uiState.update {
                    it.copy(
                        book = book,
                    )
                }
                viewModelScope.launch {
                    val isFav = async { checkIsFavourite(book.id) }
                    _uiState.update {
                        it.copy(
                            isFavourite = isFav.await()
                        )
                    }
                }
            }

    }

    private suspend fun checkIsFavourite(id: Int): Boolean {
       return favoriteUseCases.isFavoriteBookUseCase(id)
    }

    private fun addToFavourites(book: Book) {
        viewModelScope.launch {
            favoriteUseCases.insertFavouriteBookUseCase.invoke(book)
        }
    }

    private fun removeFromFavourites(book: Book) {
        viewModelScope.launch {
            favoriteUseCases.deleteFavouriteBookUseCase(book)

        }
    }
}
