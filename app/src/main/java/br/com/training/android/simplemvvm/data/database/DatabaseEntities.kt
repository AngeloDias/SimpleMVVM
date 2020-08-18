package br.com.training.android.simplemvvm.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.training.android.simplemvvm.data.domain.User

@Entity
data class DatabaseUser(
    @PrimaryKey
    val userId: Int,
    val name: String,
    val email: String,
    val avatar: String
)

fun List<DatabaseUser>.asDomainModel(): List<User> {
    return map {
        User(
            id = it.userId,
        name = it.name,
        email = it.email,
        avatar = it.avatar)
    }
}
