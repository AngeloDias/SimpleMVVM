package br.com.training.android.simplemvvm.data.mapper

import br.com.training.android.simplemvvm.data.api.ApiUser
import br.com.training.android.simplemvvm.data.domain.User

class ApiUserDataMapper: Mapper<ApiUser, User> {

    override fun map(input: ApiUser): User {
        return User(
            id = input.id,
            name = input.name,
            email = input.email,
            avatar = input.avatar
        )
    }

}
