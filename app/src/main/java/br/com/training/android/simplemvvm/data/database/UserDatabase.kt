package br.com.training.android.simplemvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.training.android.simplemvvm.data.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}