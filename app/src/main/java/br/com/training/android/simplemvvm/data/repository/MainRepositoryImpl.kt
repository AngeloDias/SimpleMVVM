package br.com.training.android.simplemvvm.data.repository

import br.com.training.android.simplemvvm.data.api.ApiHelper
import br.com.training.android.simplemvvm.data.api.ApiUser
import br.com.training.android.simplemvvm.data.domain.User
import br.com.training.android.simplemvvm.data.mapper.ListMapper
import io.reactivex.Single

class MainRepositoryImpl(
    private val apiHelper: ApiHelper,
    private val listMapper: ListMapper<ApiUser, User>): MainRepository {

    override fun getUsers(): Single<List<User>> {
        return apiHelper.getUsers().map {
            listMapper.map(it)
        }
    }

}
