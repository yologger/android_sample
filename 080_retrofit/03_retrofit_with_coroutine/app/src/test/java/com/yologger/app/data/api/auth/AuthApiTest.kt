package com.yologger.app.data.api.auth

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.runBlocking
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class AuthApiTest {
    lateinit var mockServer: MockWebServer
    lateinit var mockUrl: HttpUrl
    lateinit var client: OkHttpClient
    lateinit var authApi: AuthApi

    @Before
    fun setUp() {
        // Set up MockWebServer
        mockServer = MockWebServer()
        mockServer.start()

        // Set up Mock URL
        mockUrl = mockServer.url("/")

        // Set up Okhttp3 client
        client = OkHttpClient.Builder()
            .build()

        // Set up AuthApi
        authApi = Retrofit.Builder()
            .client(client)
            .baseUrl(mockUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @After
    fun tearDown() {
        // Shut down MockWebServer
        mockServer.shutdown()
    }

    @Test
    fun `로그인 성공 테스트`() = runBlocking {
        // Given
        val dummyUserId: Long = 1
        val dummyAccessToken = "qwekjqwlkejlkqwe"
        val dummyRefreshToken = "123jl12j3kj123lk"

        // Create mock response
        val successResponse by lazy {
            MockResponse().apply {

                val jsonObject = JsonObject()
                jsonObject.addProperty("user_id", dummyUserId)
                jsonObject.addProperty("access_token", dummyAccessToken)
                jsonObject.addProperty("refresh_token", dummyRefreshToken)

                val gson = Gson()

                val jsonString = gson.toJson(jsonObject)

                addHeader("Content-Type", "application/json")
                setResponseCode(HttpURLConnection.HTTP_OK)
                setBody(jsonString)
            }
        }

        // Add response to mock server
        mockServer.enqueue(successResponse)

        // When
        val request = LoginRequest("paul@gmail.com", "1234")

        // Then
        val response = authApi.login(request)
        assertThat(response.userId).isEqualTo(dummyUserId)
        assertThat(response.accessToken).isEqualTo(dummyAccessToken)
        assertThat(response.refreshToken).isEqualTo(dummyRefreshToken)
    }

    @Test
    fun `로그인 실패 테스트`() = runBlocking {
        // Given
        val dummyMessage = "fail"

        // Create mock response
        val successResponse by lazy {
            MockResponse().apply {

                val jsonObject = JsonObject()
                jsonObject.addProperty("message", dummyMessage)

                val gson = Gson()

                val jsonString = gson.toJson(jsonObject)

                addHeader("Content-Type", "application/json")
                setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
                setBody(jsonString)
            }
        }

        // Add response to mock server
        mockServer.enqueue(successResponse)

        // When
        val request = LoginRequest("paul@gmail.com", "1234")

        // Then
        try {
            val response = authApi.login(request)
        } catch (e: HttpException) {
            assertThat(e.code()).isEqualTo(HttpURLConnection.HTTP_BAD_REQUEST)
        } catch (e: Exception) {
            fail()
        }
    }
}