package br.com.training.android.simplemvvm.data.repository

import android.content.Context
import br.com.training.android.simplemvvm.data.api.ApiHelper
import br.com.training.android.simplemvvm.data.api.ApiUser
import br.com.training.android.simplemvvm.data.database.UserDatabase
import br.com.training.android.simplemvvm.data.domain.User
import br.com.training.android.simplemvvm.data.mapper.*
import io.reactivex.Single

class MainRepositoryImpl(
    private val apiHelper: ApiHelper,
    private val listMapper: ListMapper<ApiUser, User>): MainRepository {

    override fun getUsersFromDatabase(context: Context): List<User> {
        val listDatabaseMapper = SingleMapperImpl(DatabaseUserDataMapper())

        return UserDatabase.getInstance(context).userDatabaseDao.getAll().map {
            listDatabaseMapper.map(it)
        }
    }

    override fun getUsersFromApi(): Single<List<User>> {
        return apiHelper.getUsers().map {
            listMapper.map(it)
        }
    }

}
