package com.apple.itunesapi.data.api


import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIServiceTest {

    private lateinit var apiService: APIService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }

    private fun enqueueMockResponse(fileName: String){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        mockWebServer.enqueue(mockResponse)
    }

    @Test
    fun getMovies_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("moviesresponse.json")
            val responseBody = apiService.getMovies("star", "movie", "1").body()
            val request = mockWebServer.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/search?term=star&media=movie&limit=1")
        }
    }

    @Test
    fun getMovies_receivedRespone_correctPageSize(){
        runBlocking {
            enqueueMockResponse("moviesresponse.json")
            val responseBody = apiService.getMovies("star", "movie", "1").body()
            val movieList = responseBody!!.movies
            assertThat(movieList.size).isEqualTo(2)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("moviesresponse.json")
            val responseBody = apiService.getMovies("star", "movie", "1").body()
            val movieList = responseBody!!.movies
            val movie = movieList[1]
            assertThat(movie.trackName).isEqualTo("A Star Is Born (2020)")
            assertThat(movie.artworkUrl30).isEqualTo("https://is1-ssl.mzstatic.com/image/thumb/Video125/v4/b2/6f/29/b26f29a9-391e-e115-d0df-8fdc15b9d153/pr_source.jpg/30x30bb.jpg")
            assertThat(movie.trackPrice).isEqualTo(10.99)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}