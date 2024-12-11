package com.example.omidpaytask.useCases

import com.example.farazpardaz.domain.repository.BookExplorerRepository
import com.example.omidpaytask.domain.utils.ResultResponse
import com.example.farazpardaz.domain.model.Book
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
class SearchBookExplorerUsecaseTest {

    private lateinit var bookExplorerRepository: BookExplorerRepository
    private lateinit var searchBookExplorerUsecase: SearchBookExplorerUsecase

    @Before
    fun setup() {
        bookExplorerRepository = mockk()
        searchBookExplorerUsecase = SearchBookExplorerUsecase(bookExplorerRepository)
    }

    @Test
    fun `invoke should return Success when repository returns a list of books`() = runTest {
        // Arrange
        val searchPhrase = "test"
        val books = listOf(
            Book("Title 1", listOf("Author 1"), listOf(2021), 300, listOf("Publisher 1"), listOf("Edition 1"), listOf("English"), "key1"),
            Book("Title 2", listOf("Author 2"), listOf(2020), 250, listOf("Publisher 2"), listOf("Edition 2"), listOf("English"), "key2")
        )
        coEvery { bookExplorerRepository.searchBook(searchPhrase) } returns ResultResponse.Success(books)
        val result = searchBookExplorerUsecase.invoke(searchPhrase)
        assert(result is ResultResponse.Success)
        assertEquals(books, (result as ResultResponse.Success).data)
    }

    @Test
    fun `invoke should return Failure when repository throws an exception`() = runTest {
        val searchPhrase = "test"
        val exception = Exception("Network Error")
        coEvery { bookExplorerRepository.searchBook(searchPhrase) } returns ResultResponse.Failure(exception)
        val result = searchBookExplorerUsecase.invoke(searchPhrase)
        assert(result is ResultResponse.Failure)
        assertEquals("Network Error", (result as ResultResponse.Failure).exception.message)
    }
}
