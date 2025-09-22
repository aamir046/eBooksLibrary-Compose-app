package com.aamir.compose.eBooksLibrary.data.remote.api

import com.aamir.compose.eBooksLibrary.data.remote.dto.BookDto

class BookApi {
    fun getBooks(): List<BookDto> =
        listOf(
            BookDto(
                id = 1,
                author = "Harper Lee",
                title = "To Kill a Mockingbird",
                year = "1960",
                description = "A story of racial injustice and childhood innocence in the Deep South.",
                imageUrl = "https://images.gr-assets.com/books/1553383690l/2657.jpg",
                type = "UPCOMING"
            ),
            BookDto(
                id = 2,
                author = "George Orwell",
                title = "1984",
                year = "1949",
                description = "A chilling vision of a dystopian world under total surveillance.",
                imageUrl = "https://images.penguinrandomhouse.com/cover/9780679417392",
                type = "UPCOMING"
            ),
            BookDto(
                id = 3,
                author = "J.K. Rowling",
                title = "Harry Potter and the Sorcerer's Stone",
                year = "1997",
                description = "The beginning of Harry Potter’s magical journey at Hogwarts.",
                imageUrl = "https://images.gr-assets.com/books/1474154022l/3.jpg",
                type = "UPCOMING"
            ),
            BookDto(
                id = 4,
                author = "J.R.R. Tolkien",
                title = "The Lord of the Rings",
                year = "1954",
                description = "An epic fantasy quest to destroy the One Ring and defeat evil.",
                imageUrl = "https://images.gr-assets.com/books/1566425108l/33.jpg",
                type = "UPCOMING"
            ),
            BookDto(
                id = 5,
                author = "F. Scott Fitzgerald",
                title = "The Great Gatsby",
                year = "1925",
                description = "A tragic love story in the Roaring Twenties.",
                imageUrl = "https://images.gr-assets.com/books/1490528560l/4671.jpg",
                type = "RECOMMENDED"
            ),
            BookDto(
                id = 6,
                author = "Jane Austen",
                title = "Pride and Prejudice",
                year = "1813",
                description = "A witty romantic drama about class and manners.",
                imageUrl = "https://images.gr-assets.com/books/1320399351l/1885.jpg",
                type = "RECOMMENDED"
            ),
            BookDto(
                id = 7,
                author = "Markus Zusak",
                title = "The Book Thief",
                year = "2005",
                description = "A girl’s love for books during WWII in Nazi Germany.",
                imageUrl = "https://images.gr-assets.com/books/1522157426l/19063.jpg",
                type = "RECOMMENDED"
            ),
            BookDto(
                id = 8,
                author = "Khaled Hosseini",
                title = "The Kite Runner",
                year = "2003",
                description = "A tale of friendship, guilt, and redemption in Afghanistan.",
                imageUrl = "https://images.gr-assets.com/books/1484565687l/77203.jpg",
                type = "RECOMMENDED"
            ),
            BookDto(
                id = 9,
                author = "J.D. Salinger",
                title = "The Catcher in the Rye",
                year = "1951",
                description = "A teen’s journey through alienation and rebellion.",
                imageUrl = "https://images.gr-assets.com/books/1398034300l/5107.jpg",
                type = "RECOMMENDED"
            ),
            BookDto(
                id = 10,
                author = "Stephen King",
                title = "The Shining",
                year = "1977",
                description = "A family's terrifying stay in an isolated haunted hotel.",
                imageUrl = "https://images.gr-assets.com/books/1353277730l/11588.jpg",
                type = "RECOMMENDED"
            ),
            BookDto(
                id = 11,
                author = "Paulo Coelho",
                title = "The Alchemist",
                year = "1988",
                description = "A shepherd’s journey to realize his personal legend.",
                imageUrl = "https://images.gr-assets.com/books/1483412266l/865.jpg",
                type = "POPULAR"
            ),
            BookDto(
                id = 12,
                author = "Dan Brown",
                title = "The Da Vinci Code",
                year = "2003",
                description = "A symbologist uncovers secrets hidden in art and history.",
                imageUrl = "https://danbrown.com/wp-content/uploads/2024/10/Dan-Brown_The-Da-Vinci-Code-book-cover_2024.jpg",
                type = "POPULAR"
            ),
            BookDto(
                id = 13,
                author = "Suzanne Collins",
                title = "The Hunger Games",
                year = "2008",
                description = "A dystopian fight for survival in a televised death match.",
                imageUrl = "https://images.gr-assets.com/books/1447303603l/2767052.jpg",
                type = "POPULAR"
            ),
            BookDto(
                id = 14,
                author = "Charlotte Brontë",
                title = "Jane Eyre",
                year = "1847",
                description = "A gothic tale of romance and independence.",
                imageUrl = "https://images.gr-assets.com/books/1327867269l/10210.jpg",
                type = "POPULAR"
            ),
            BookDto(
                id = 15,
                author = "Ray Bradbury",
                title = "Fahrenheit 451",
                year = "1953",
                description = "A future society where books are outlawed and burned.",
                imageUrl = "https://images.gr-assets.com/books/1383718290l/13079982.jpg",
                type = "TOP_SEARCHED"
            ),
            BookDto(
                id = 16,
                author = "Margaret Atwood",
                title = "The Handmaid’s Tale",
                year = "1985",
                description = "A dystopian society where women are forced into childbearing servitude.",
                imageUrl = "https://upload.wikimedia.org/wikipedia/en/1/18/TheHandmaidsTale%281stEd%29.jpg",
                type = "TOP_SEARCHED"
            ),
            BookDto(
                id = 17,
                author = "Gabriel García Márquez",
                title = "One Hundred Years of Solitude",
                year = "1967",
                description = "A magical realist epic spanning generations in Macondo.",
                imageUrl = "https://images.gr-assets.com/books/1327881361l/320.jpg",
                type = "TOP_SEARCHED"
            ),
            BookDto(
                id = 18,
                author = "John Green",
                title = "The Fault in Our Stars",
                year = "2012",
                description = "A heartbreaking romance between two teens with cancer.",
                imageUrl = "https://images.gr-assets.com/books/1360206420l/11870085.jpg",
                type = "TOP_SEARCHED"
            ),
            BookDto(
                id = 19,
                author = "Yann Martel",
                title = "Life of Pi",
                year = "2001",
                description = "A boy stranded in a lifeboat with a Bengal tiger.",
                imageUrl = "https://images.gr-assets.com/books/1320562005l/4214.jpg",
                type = "NEW_RELEASED"
            ),
            BookDto(
                id = 20,
                author = "Leo Tolstoy",
                title = "Anna Karenina",
                year = "1878",
                description = "A tragic romance exploring Russian high society.",
                imageUrl = "https://www.gutenberg.org/cache/epub/1399/pg1399.cover.medium.jpg",
                type = "NEW_RELEASED"
            )
        )
}
