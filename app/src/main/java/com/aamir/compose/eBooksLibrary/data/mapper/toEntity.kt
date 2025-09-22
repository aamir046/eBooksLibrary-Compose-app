package com.aamir.compose.eBooksLibrary.data.mapper

import com.aamir.compose.eBooksLibrary.data.local.entity.BookEntity
import com.aamir.compose.eBooksLibrary.data.remote.dto.BookDto
import com.aamir.compose.eBooksLibrary.domain.model.Book

fun BookDto.toEntity() = BookEntity(id, author, title, year, description, imageUrl, type)
fun BookEntity.toDomain() = Book(id, author, title, year, description, imageUrl, type, isFavourite)
fun BookDto.toDomain() = Book(id, author, title, year, description, imageUrl, type)
