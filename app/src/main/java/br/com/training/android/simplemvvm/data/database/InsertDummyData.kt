package br.com.training.android.simplemvvm.data.database

import android.content.Context

class InsertDummyData {

    companion object {
        fun insert(context: Context){
            val databaseUser = DatabaseUser(
                name = "Tester",
                email = "mine@yahoo.com.br",
                avatar = "",
                userId = 7
            )

            val userDao = UserDatabase.getInstance(context).userDatabaseDao

            if(userDao.get(7) == null) {
                userDao.insertAll(databaseUser)
            }

        }
    }

}