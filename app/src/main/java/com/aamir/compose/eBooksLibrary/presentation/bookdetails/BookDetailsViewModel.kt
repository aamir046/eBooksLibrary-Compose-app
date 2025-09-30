package com.aamir.compose.eBooksLibrary.presentation.bookdetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamir.compose.eBooksLibrary.domain.interactor.book.FavoriteUseCases
import com.aamir.compose.eBooksLibrary.domain.model.Book
import com.aamir.compose.eBooksLibrary.presentation.home.HomeScreenActions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookDetailsViewModel(
    private val favoriteUseCases: FavoriteUseCases
):ViewModel(){
    private val _uiState = MutableStateFlow(BookDetailsScreenState())

    val uiState = _uiState.onStart {
     //   updateBookDetails()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _uiState.value
    )

    val onActions: (BookDetailsScreenActions) -> Unit = { action ->
        when (action) {
            is BookDetailsScreenActions.OnToggleFavorite -> {
                if(_uiState.value.isFavourite){
                    removeFromFavourites(_uiState.value.book!!)
                }else{
                    addToFavourites(_uiState.value.book!!.copy(isFavourite = true))
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