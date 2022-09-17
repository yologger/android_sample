package com.yologger.app.data.repository

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.yologger.app.data.api.auth.AuthApi
import com.yologger.app.domain.login.LoginResult
import com.yologger.app.domain.repository.AuthRepository
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class AuthRepositoryImplTest {

    lateinit var mockServer: MockWebServer
    lateinit var mockUrl: HttpUrl
    lateinit var client: OkHttpClient
    lateinit var authApi: AuthApi
    lateinit var authRepository: AuthRepository

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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(AuthApi::class.java)

        authRepository = AuthRepositoryImpl(authApi, Gson())
    }

    @After
    fun tearDown() {
        // Shut down MockWebServer
        mockServer.shutdown()
    }

    @Test
    fun `로그인 성공 테스트`() {
        // Given
        val dummyUserId: Long = 1
        val dummyAccessToken = "dummy_access_token"
        val dummyRefreshToken = "dummy_refresh_token"

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

        val dummyEmail = "ronaldo@gmail.com"
        val dummyPassword = "12341234"

        authRepository.login(dummyEmail, dummyPassword)
            .blockingSubscribe { result ->
                assertThat(result is LoginResult.SUCCESS).isTrue()
                assertThat((result as LoginResult.SUCCESS).data.accessToken).isEqualTo(dummyAccessToken)
                assertThat((result as LoginResult.SUCCESS).data.refreshToken).isEqualTo(dummyRefreshToken)
            }
    }
}