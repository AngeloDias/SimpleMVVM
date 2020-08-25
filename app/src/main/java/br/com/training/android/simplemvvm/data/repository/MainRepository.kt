package br.com.training.android.simplemvvm.data.repository

import android.content.Context
import br.com.training.android.simplemvvm.data.domain.User
import io.reactivex.Single

interface MainRepository {
    fun getUsersFromDatabase(context: Context): List<User>
    fun getUsersFromApi(): Single<List<User>>
}