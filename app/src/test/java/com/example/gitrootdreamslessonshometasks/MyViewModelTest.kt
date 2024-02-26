package com.example.gitrootdreamslessonshometasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.internal.http.hasBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import retrofit2.Response

class MyViewModelTest {

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun getSuccessResponse(){
        val repository = Mockito.mock(Repository::class.java)
        var successfulResponseBody : BitcoinResponse = BitcoinResponse(Data("bitcoin","BTC","₿","5200.0"))
        val successfulResponse = Response.success(successfulResponseBody)
        val viewModel = MyViewModel(repository)
        val eventList = mutableListOf<MyViewModel.UIState>()
        viewModel.uiState.observeForever {
            eventList.add(it)
        }

        runBlocking(Dispatchers.IO) {
            Mockito.`when`(repository.getCurrencyByName("bitcoin")).thenReturn(successfulResponse)
        }

        viewModel.getData()

        Assert.assertEquals(MyViewModel.UIState.Empty,eventList[0])
        Assert.assertEquals(MyViewModel.UIState.Processing,eventList[1])
        runBlocking(Dispatchers.IO) {
            delay(2000)
        }
        val uiState = eventList[2] as MyViewModel.UIState.Result
        Assert.assertEquals("bitcoin 5200.0", uiState.title)
    }

    @Test
    fun getNullResponse() {
        val repository = Mockito.mock(Repository::class.java)
        var nullResponseBody : BitcoinResponse = BitcoinResponse(null)
        val nullResponse = Response.success(nullResponseBody)
        val viewModel = MyViewModel(repository)
        val eventList = mutableListOf<MyViewModel.UIState>()
        viewModel.uiState.observeForever {
            eventList.add(it)
        }

        runBlocking(Dispatchers.IO) {
            Mockito.`when`(repository.getCurrencyByName("bitcoin")).thenReturn(nullResponse)
        }

        viewModel.getData()
        Assert.assertEquals(MyViewModel.UIState.Empty,eventList[0])
        Assert.assertEquals(MyViewModel.UIState.Processing,eventList[1])

        runBlocking(Dispatchers.IO) {
            delay(2000)
        }

        val uiState = eventList[2] as MyViewModel.UIState.Error
        Assert.assertEquals("Error: Null Response", uiState.description)
    }

    @Test
    fun getErrorResponse() {
        val repository = Mockito.mock(Repository::class.java)
        val errorResponse =
            "{\n" + "\"type\": \"error\",\n" + "\"message\": \"This is not the web page you are looking for.\"\n" + "}"
        val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
        val mockResponse = Response.error<BitcoinResponse>(404, errorResponseBody)

        val viewModel = MyViewModel(repository)
        val eventList = mutableListOf<MyViewModel.UIState>()
        viewModel.uiState.observeForever {
            eventList.add(it)
        }
        runBlocking(Dispatchers.IO) {
            Mockito.`when`(repository.getCurrencyByName("bitcoin")).thenReturn(mockResponse)
        }

        viewModel.getData()
        Assert.assertEquals(MyViewModel.UIState.Empty,eventList[0])
        Assert.assertEquals(MyViewModel.UIState.Processing,eventList[1])

        runBlocking(Dispatchers.IO) {
            delay(2000)
        }

        val uiState = eventList[2] as MyViewModel.UIState.Error
        Assert.assertEquals("Error Code 404", uiState.description)
    }

    @Test
    fun catchErrorException() {
        val repository = Mockito.mock(Repository::class.java)
        val errorResponse = "Error on response"
        val errorResponseBody = errorResponse.toResponseBody("application/json".toMediaTypeOrNull())
        val mockResponse = Response.error<BitcoinResponse>(500, errorResponseBody)
        val viewModel = MyViewModel(repository)
        val eventList = mutableListOf<MyViewModel.UIState>()
        viewModel.uiState.observeForever {
            eventList.add(it)
        }

        runBlocking(Dispatchers.IO) {
            Mockito.`when`(repository.getCurrencyByName("bitcoin"))
                .thenThrow(RuntimeException())
                .thenReturn(mockResponse)
        }

        viewModel.getData()
        Assert.assertEquals(MyViewModel.UIState.Empty,eventList[0])
        Assert.assertEquals(MyViewModel.UIState.Processing,eventList[1])

        runBlocking(Dispatchers.IO) {
            delay(100)
        }
        val uiState = eventList[2] as MyViewModel.UIState.Error
        Assert.assertEquals("Error on response", uiState.description)
    }
}

