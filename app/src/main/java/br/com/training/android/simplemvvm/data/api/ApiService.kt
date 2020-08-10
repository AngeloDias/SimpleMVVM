package br.com.training.android.simplemvvm.data.api

import br.com.training.android.simplemvvm.data.model.User
import io.reactivex.Single

interface ApiService {

    fun getUsers(): Single<List<User>>

}
