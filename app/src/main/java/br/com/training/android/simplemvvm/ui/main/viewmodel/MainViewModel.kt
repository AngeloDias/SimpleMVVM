package br.com.training.android.simplemvvm.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.training.android.simplemvvm.data.api.ApiUser
import br.com.training.android.simplemvvm.data.repository.MainRepositoryImpl
import br.com.training.android.simplemvvm.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * TODO refactor to exclude User direct use from the ViewModel
 * */
class MainViewModel(private val mainRepositoryImpl: MainRepositoryImpl) : ViewModel() {

    private val users = MutableLiveData<Resource<List<ApiUser>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        users.postValue(Resource.loading(null))

        compositeDisposable
            .add(mainRepositoryImpl
                .getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                            userList -> users.postValue(Resource.success(userList))
                    },
                    {
                        users.postValue(Resource.error("Something Went Wrong", null))
                    }
                )
            )

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }

}
