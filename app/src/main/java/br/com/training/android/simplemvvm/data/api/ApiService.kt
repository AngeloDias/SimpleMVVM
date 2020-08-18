package br.com.training.android.simplemvvm.data.api

import io.reactivex.Single

interface ApiService {

    fun getUsers(): Single<List<ApiUser>>

}
