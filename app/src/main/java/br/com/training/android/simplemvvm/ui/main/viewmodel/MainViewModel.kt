package br.com.training.android.simplemvvm.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.training.android.simplemvvm.data.database.InsertDummyData
import br.com.training.android.simplemvvm.data.domain.User
import br.com.training.android.simplemvvm.data.repository.MainRepositoryImpl
import br.com.training.android.simplemvvm.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

class MainViewModel(
    private val mainRepositoryImpl: MainRepositoryImpl,
    private val context: Context) : ViewModel() {

    // region Defining properties
    private val defaultErrorMessage = "Something Went Wrong"
    // endregion

    //region Using RxAndroid

    private val users = MutableLiveData<Resource<List<User>>>()
    private val compositeDisposable = CompositeDisposable()

    //endregion

    //region Using coroutines

    private lateinit var viewModelJob: Job
    private lateinit var viewModelScope: CoroutineScope

    //endregion

    init {
        users.postValue(Resource.loading(null))
//        fetchUsers()
        fetchUsersUsingCoroutines()
    }

    private fun fetchUsersUsingCoroutines() {
        viewModelJob = SupervisorJob()
        viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)
        val handler = CoroutineExceptionHandler { _, exception ->
            users.postValue(Resource.error(exception.message ?: defaultErrorMessage, null))
        }

        viewModelScope.launch(Dispatchers.Main + handler) {
            withContext(Dispatchers.IO) {
                InsertDummyData.insert(context)
                val databaseUsers = mainRepositoryImpl.getUsersFromDatabase(context)

                users.postValue(Resource.success(databaseUsers))
            }
        }

    }

    private fun fetchUsers() {
//        users.postValue(Resource.loading(null))

        compositeDisposable
            .add(mainRepositoryImpl
                .getUsersFromApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                            userList -> users.postValue(Resource.success(userList))
                    },
                    {
                        users.postValue(Resource.error(defaultErrorMessage, null))
                    }
                )
            )

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        viewModelJob.cancel()
    }

    fun getUsers(): LiveData<Resource<List<User>>> {
        return users
    }

}
