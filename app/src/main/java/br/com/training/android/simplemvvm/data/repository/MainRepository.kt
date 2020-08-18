package br.com.training.android.simplemvvm.data.repository

import br.com.training.android.simplemvvm.data.api.ApiHelper
import br.com.training.android.simplemvvm.data.api.ApiUser
import io.reactivex.Single

/**
 * TODO refactor to expose the domain's "User" class instead of the "ApiUser" class
 * */
class MainRepository(private val apiHelper: ApiHelper) {

    fun getUsers(): Single<List<ApiUser>> {
        return apiHelper.getUsers()
    }

}
