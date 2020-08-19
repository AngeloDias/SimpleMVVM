package br.com.training.android.simplemvvm.data.api

import android.content.Context
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

class ApiServiceImpl : ApiService {

    override fun getUsers(): Single<List<ApiUser>> {
        return Rx2AndroidNetworking.get("https://5e510330f2c0d300147c034c.mockapi.io/users")
            .build()
            .getObjectListSingle(ApiUser::class.java)
    }

}

class ApiServiceFactory {
    @Volatile
    private var INSTANCE: ApiServiceImpl? = null

    fun getInstance(context: Context): ApiService {
        synchronized(this) {
            var instance = INSTANCE as ApiService

            if(instance == null) {
            }

            INSTANCE = instance as ApiServiceImpl

            return instance
        }

    }
}