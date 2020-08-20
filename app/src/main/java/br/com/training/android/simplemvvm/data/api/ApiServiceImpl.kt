package br.com.training.android.simplemvvm.data.api

import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

internal class ApiServiceImpl: ApiService {

    override fun getUsers(): Single<List<ApiUser>> {
        return Rx2AndroidNetworking.get("https://5e510330f2c0d300147c034c.mockapi.io/users")
            .build()
            .getObjectListSingle(ApiUser::class.java)
    }

    override fun getInstance(): ApiService {
        return ApiServiceFactory.getInstance()
    }

}

private class ApiServiceFactory {

    companion object {
        @Volatile
        private var INSTANCE: ApiService? = null

        fun getInstance(): ApiService {
            synchronized(this) {
                var instance = INSTANCE as ApiService

                if (instance == null) {
                    instance = ApiServiceImpl()
                }

                INSTANCE = instance

                return instance
            }

        }

    }
}