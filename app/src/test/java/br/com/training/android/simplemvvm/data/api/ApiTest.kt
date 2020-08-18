package br.com.training.android.simplemvvm.data.api

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ApiTest {
    @MockK
    private lateinit var apiServiceImpl: ApiServiceImpl

    @Before
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun apiServiceGetUsersTest() {
        val singleMockk = mockkClass(Single::class)

        every { apiServiceImpl.getUsers() } returns singleMockk as Single<List<ApiUser>>

        Assert.assertNotNull(apiServiceImpl.getUsers())

        verify { apiServiceImpl.getUsers() }
        confirmVerified(apiServiceImpl)
    }

    @Test
    fun apiHelperGetUsersTest() {
        val apiHelperMockk = mockk<ApiHelper>()
        val singleMockk = mockkClass(Single::class)

        every { apiHelperMockk.getUsers() } returns singleMockk as Single<List<ApiUser>>

        Assert.assertEquals(singleMockk, apiHelperMockk.getUsers())

        verify { apiHelperMockk.getUsers() }
        confirmVerified(apiHelperMockk)
    }

}
