package br.com.training.android.simplemvvm.data.mapper

import br.com.training.android.simplemvvm.data.database.DatabaseUser
import br.com.training.android.simplemvvm.data.domain.User

class DatabaseUserDataMapper: Mapper<DatabaseUser, User> {

    override fun map(input: DatabaseUser): User {
        return User(
            id = input.userId,
            name = input.name,
            email = input.email,
            avatar = input.avatar
        )
    }

}
