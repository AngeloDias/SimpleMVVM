package br.com.training.android.simplemvvm.data.repository

import br.com.training.android.simplemvvm.data.api.ApiHelper
import br.com.training.android.simplemvvm.data.model.User
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getUsers(): Single<List<User>> {
        return apiHelper.getUsers()
    }

}
