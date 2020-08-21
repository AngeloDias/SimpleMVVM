package br.com.training.android.simplemvvm.data.repository

import br.com.training.android.simplemvvm.data.domain.User
import io.reactivex.Single

interface MainRepository {
    fun getUsers(): Single<List<User>>
}