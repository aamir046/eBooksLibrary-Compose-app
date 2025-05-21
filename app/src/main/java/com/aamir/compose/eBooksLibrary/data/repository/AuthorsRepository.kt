package com.aamir.compose.eBooksLibrary.data.repository

import com.aamir.compose.eBooksLibrary.domain.Author
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AuthorsRepository {
    fun fetchAuthors(): Flow<List<Author>> =
        flowOf(
            listOf(
                Author(
                    name = "Jane Austen",
                    category = "Novelist",
                    about = "Renowned for her keen observations of 18th-century English society and themes of love and marriage.",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/c/cd/Jane_Austen_coloured_version.jpg",
                    rating = 4.5
                ),
                Author(
                    name = "George Orwell",
                    category = "Novelist, Essayist",
                    about = "Known for his lucid prose and opposition to totalitarianism.",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/9/9e/George_Orwell_press_photo.jpg",
                    rating = 4.4
                ),
                Author(
                    name = "Virginia Woolf",
                    category = "Novelist, Essayist",
                    about = "Central figure in modernist literature; explored deep psychological themes.",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/0/0b/Virginia_Woolf_1927.jpg",
                    rating = 4.3
                ),
                Author(
                    name = "Mark Twain",
                    category = "Novelist, Humorist",
                    about = "American literary icon known for his wit and social commentary.",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/0/0c/Mark_Twain_by_AF_Bradley.jpg",
                    rating = 4.2
                ),
                Author(
                    name = "Agatha Christie",
                    category = "Novelist",
                    about = "Queen of Mystery, known for detective fiction that shaped the genre.",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/1/1f/Agatha_Christie.png",
                    rating = 4.1
                ),
                Author(
                    name = "Ernest Hemingway",
                    category = "Novelist, Short Story Writer",
                    about = "Pioneer of modern American literature known for concise prose.",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/2/28/ErnestHemingway.jpg",
                    rating = 4.0
                ),
                Author(
                    name = "J.K. Rowling",
                    category = "Novelist",
                    about = "Author of the Harry Potter series, one of the most successful franchises in history.",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/5/5d/J._K._Rowling_2010.jpg",
                    rating = 3.9
                ),
                Author(
                    name = "Leo Tolstoy",
                    category = "Novelist",
                    about = "Russian author of epic masterpieces exploring moral and philosophical themes.",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/7/7f/Leo_Tolstoy%2C_portrait.jpg",
                    rating = 4.6
                ),
                Author(
                    name = "Gabriel García Márquez",
                    category = "Novelist",
                    about = "Master of magical realism, blending reality and fantasy seamlessly.",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/1/1d/Gabriel_Garcia_Marquez.jpg",
                    rating = 4.4
                ),
                Author(
                    name = "Toni Morrison",
                    category = "Novelist",
                    about = "Nobel laureate known for powerful explorations of African-American identity.",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/4/44/Toni_Morrison_2008-2.jpg",
                    rating = 4.5
                )
            )
        )
}