package br.com.training.android.simplemvvm.data.database

import androidx.room.*

@Dao
interface UserDatabaseDao {
    @Query("SELECT * FROM DatabaseUser")
    fun getAll(): List<DatabaseUser>

    @Query("SELECT * FROM DatabaseUser WHERE userId IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<DatabaseUser>

    @Query("SELECT * from DatabaseUser WHERE userId = :key")
    fun get(key: Long): DatabaseUser?

    @Update
    fun update(user: DatabaseUser)

    @Insert
    fun insertAll(vararg users: DatabaseUser)

    @Delete
    fun delete(user: DatabaseUser)

    /**
     * Deletes all values from the table and only the values, not the table.
     */
    @Query("DELETE FROM DatabaseUser")
    fun clear()
}
