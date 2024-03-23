package ru.bogdan.m14_retrofit.data

import ru.bogdan.m14_retrofit.domain.SimpleUser
import ru.bogdan.m14_retrofit.data.pojo.User
import javax.inject.Inject

class UserMapper@Inject constructor() {
    fun getSimpleUserFromUser(user: User):SimpleUser{
        return SimpleUser(
            name = user.results[0].name.first,
            secondName = user.results[0].name.last,
            phone = user.results[0].phone,
            email = user.results[0].email,
            city = user.results[0].location.city,
            country = user.results[0].location.country,
            nationality = user.results[0].nat,
            imageUrl = user.results[0].picture.large,
            age = user.results[0].dob.age.toString()
        )
    }
}