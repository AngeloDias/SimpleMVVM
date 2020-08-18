package br.com.training.android.simplemvvm.data.repository

import br.com.training.android.simplemvvm.data.domain.User
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.hamcrest.collection.IsCollectionWithSize
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainRepositoryTest {

    @Before
    fun setup() = MockKAnnotations.init(this)

    @Test
    fun singleTest() {
        val usersList = mockk<List<User>>()
        val single = Single.just(usersList)
        val testObserver = single.test() //TestObserver<List<User>>()

        every { usersList.size } returns 7

        single.subscribe(testObserver)

        val dataEmitted = testObserver.values()[0]

        Assert.assertThat(dataEmitted, IsCollectionWithSize.hasSize(7))

        val userExpected = mockk<User>()
        every { usersList[0] } returns userExpected

        Assert.assertEquals(userExpected, usersList[0])
    }
}