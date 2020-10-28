package com.thescore.proficiencyexercise.screens.retrofitesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.thescore.proficiencyexercise.data.entity.TeamResponse
import com.thescore.proficiencyexercise.ui.memberlist.TeamListViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.json.JSONObject
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class RequestViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TeamListViewModel

    private lateinit var apiHelper: RetrofitBuilder

    @Mock
    private lateinit var apiEmployeeObserver: Observer<MutableList<TeamResponse>>

    private lateinit var mockWebServer: MockWebServer


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = TeamListViewModel(null)
        viewModel.getTeamList().observeForever(apiEmployeeObserver)

        mockWebServer = MockWebServer()
        mockWebServer.start()
        apiHelper = (RetrofitBuilder)
    }

    @Test
    fun read_sample_success_json_file(){
        val reader = MockResponseFileReader("success_resp.json")
        assertNotNull(reader.content)
    }

    @Test
    suspend fun fetch_details_and_check_response_Code_200_returned(){
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("success_response.json").content)
        mockWebServer.enqueue(response)
        // Act
        val  actualResponse = apiHelper.apiInterface.getAllTeamASync()
        // Assert
        assertEquals(response.toString().contains("200"),actualResponse.toString().contains("200"))
    }

    @Test
    suspend fun fetch_details_and_check_response_success_returned(){
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(MockResponseFileReader("success_response.json").content)
        mockWebServer.enqueue(response)
        val mockResponse = response.getBody()?.readUtf8()
        // Act
        val  actualResponse = apiHelper.apiInterface.getAllTeamASync()
        // Assert
        assertEquals(mockResponse?.let { parse_mocked_JSON_response(it) }, actualResponse)
    }

    private fun parse_mocked_JSON_response(mockResponse: String): String {
        val reader = JSONObject(mockResponse)
        return reader.getString("wins")
    }

    @Test
    suspend fun fetch_details_for_failed_response_400_returned(){
        // Assign
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(MockResponseFileReader("failed_response.json").content)
        mockWebServer.enqueue(response)
        // Act
        val  actualResponse = apiHelper.apiInterface.getAllTeamASync()
        // Assert
        assertEquals(response.toString().contains("400"),actualResponse.toString().contains("400"))
    }

    @After
    fun tearDown() {
        viewModel.getTeamList().removeObserver(apiEmployeeObserver)
        mockWebServer.shutdown()
    }
}