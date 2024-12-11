package com.example.omidpaytask.viewModel

import androidx.lifecycle.Observer
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel
    private lateinit var searchBooksUseCase: SearchBookExplorerUsecase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        searchBooksUseCase = mockk()
        viewModel = SearchViewModel(searchBooksUseCase)
        Dispatchers.setMain(UnconfinedTestDispatcher()) // Set the main dispatcher
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher
    }

    // تست وضعیت Loading
    @Test
    fun `searchBooks should set loading state initially and then update with results`() = runTest {
        val query = "test"
        val observer = mockk<Observer<BookState>>(relaxed = true)
        viewModel.state.observeForever(observer)
        coEvery { searchBooksUseCase.invoke(query) } returns ResultResponse.Success(emptyList())
        viewModel.searchBooks(query)
        verify { observer.onChanged(BookState.Loading) }
        viewModel.state.removeObserver(observer)
    }

    // تست وضعیت Loading
    @Test
    fun `searchBooks should set loading then receive a list of book from invoke and set state to success`() =
        runTest {
            val quere = "test"
            val expectedBooks = listOf(
                Book(
                    title = "Title 1",
                    authorNames = listOf("Author 1"),
                    publishYear = listOf(2021),
                    numberOfPages = 300,
                    publisher = listOf("Publisher 1"),
                    editionKey = listOf("EditionKey1"),
                    language = listOf("English"),
                    key = "Key1"
                ),
                Book(
                    title = "Title 2",
                    authorNames = listOf("Author 2"),
                    publishYear = listOf(2022),
                    numberOfPages = 250,
                    publisher = listOf("Publisher 2"),
                    editionKey = listOf("EditionKey2"),
                    language = listOf("English"),
                    key = "Key2"
                )
            )
            coEvery { searchBooksUseCase.invoke(quere) } returns ResultResponse.Success(
                expectedBooks
            )
            val observer = mockk<Observer<BookState>>(relaxed = true)
            viewModel.state.observeForever(observer)
            viewModel.searchBooks(quere)
            verifyOrder {
                observer.onChanged(BookState.Loading)
                observer.onChanged(BookState.Success(expectedBooks))
            }
            viewModel.state.removeObserver(observer)

        }

    @Test
    fun `searchBooks should set loading then receive a faild  from invoke and set state to Error`() =
        runTest {
            val quere = "test"
            val exception = Exception("Network Error")
            coEvery { searchBooksUseCase.invoke(quere) } returns ResultResponse.Failure(exception)
            val observer = mockk<Observer<BookState>>(relaxed = true)
            viewModel.state.observeForever(observer)
            viewModel.searchBooks(quere)
            verifyOrder {
                observer.onChanged(BookState.Loading)
                observer.onChanged(BookState.Error("Failed to fetch books: Network Error"))
            }
            viewModel.state.removeObserver(observer)

        }

    @Test
    fun `searchBooks should set loading then receive empty list from invoke and set state to Empty`() =
        runTest {
            val query = "test"
            val emptyBooksList = emptyList<Book>()
            coEvery { searchBooksUseCase.invoke(query) } returns ResultResponse.Success(
                emptyBooksList
            )

            val observer = mockk<Observer<BookState>>(relaxed = true)
            viewModel.state.observeForever(observer)

            viewModel.searchBooks(query)

            verifyOrder {
                observer.onChanged(BookState.Loading)
                observer.onChanged(BookState.Empty)
            }

            viewModel.state.removeObserver(observer)
        }
}