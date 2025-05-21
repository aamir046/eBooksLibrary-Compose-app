package com.aamir.compose.eBooksLibrary.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.aamir.compose.eBooksLibrary.core.presentation.LoadRemoteImage
import com.aamir.compose.eBooksLibrary.domain.Book
import com.aamir.compose.eBooksLibrary.presentation.theme.Gray
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@Composable
fun SectionItemUpcomingBooks(books:List<Book>,autoScroll:Boolean = true){
    UpComingBooksLaunchPager(books = books,autoScroll = autoScroll )
}

@Composable
fun UpComingBooksLaunchPager(books:List<Book>,autoScroll:Boolean = true){

    if (books.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { books.size })

    //Auto-scroll every 3 seconds
    LaunchedEffect(autoScroll, books.size){
        if (!autoScroll || books.size <= 1) return@LaunchedEffect
        while (true) {
            delay(3000L)
            val nextPage = (pagerState.currentPage + 1) % books.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(align = Alignment.Center)) {
        Text(
            text = "Upcoming Books",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
            pageSpacing = 8.dp,
            userScrollEnabled = false
        ) { page ->

            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

            val scale = lerp(0.55f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
            val alpha = lerp(0.5f, 1f, 1f - pageOffset.coerceIn(0f, 1f))

            Box(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
            ) {
                UpComingBooksLaunchCard(book = books[page])
            }
        }
        // Indicators
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center) {
            repeat(pagerState.pageCount) { index ->
                val isSelected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.CenterVertically)
                        .size(if (isSelected) 12.dp else 8.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color.Black else Color.Gray)
                )
            }
        }
    }

}


@Composable
fun UpComingBooksLaunchCard(book: Book){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .shadow(elevation = 10.dp, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
    ) {
        Row(modifier = Modifier.fillMaxSize().background(Gray)) {
            Column(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.65f)) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 20.dp, start = 30.dp, end = 20.dp).fillMaxWidth()
                )

                Text(
                    text = book.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 8.dp, start = 30.dp, end = 20.dp).fillMaxWidth()
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Author: ${book.author}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 8.dp, start = 30.dp, end = 20.dp).fillMaxWidth()
                )
            }

            Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
                LoadRemoteImage(
                    url = book.imageUrl,
                    contentDescription = "Book Cover Picture",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

}

@Preview(apiLevel = 34, showBackground = true, device = Devices.PIXEL)
@Composable
fun UpComingBooksLaunchCardPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            SectionItemUpcomingBooks(
                listOf(
                    Book(
                        author = "Harper Lee",
                        title = "To Kill a Mockingbird",
                        year = "1960",
                        description = "A story of racial injustice and childhood innocence in the Deep South.",
                        imageUrl = "https://images.gr-assets.com/books/1553383690l/2657.jpg"
                    ),
                    Book(
                        author = "George Orwell",
                        title = "1984",
                        year = "1949",
                        description = "A chilling vision of a dystopian world under total surveillance.",
                        imageUrl = "https://images.gr-assets.com/books/1532714506l/5470.jpg"
                    ),
                    Book(
                        author = "J.K. Rowling",
                        title = "Harry Potter and the Sorcerer's Stone",
                        year = "1997",
                        description = "The beginning of Harry Potter’s magical journey at Hogwarts.",
                        imageUrl = "https://images.gr-assets.com/books/1474154022l/3.jpg"
                    ),
                    Book(
                        author = "J.R.R. Tolkien",
                        title = "The Lord of the Rings",
                        year = "1954",
                        description = "An epic fantasy quest to destroy the One Ring and defeat evil.",
                        imageUrl = "https://images.gr-assets.com/books/1566425108l/33.jpg"
                    )
                )
            )
        }
    }

}