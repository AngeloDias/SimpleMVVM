package br.com.training.android.simplemvvm.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseUser::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract val userDatabaseDao: UserDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        /**
         * @param context The application context Singleton, used to get access to the filesystem.
         * */
        fun getInstance(context: Context): UserDatabase {

            synchronized(this) {
                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE

                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_history_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }

                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }

}
