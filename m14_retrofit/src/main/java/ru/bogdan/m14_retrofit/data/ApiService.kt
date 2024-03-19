package ru.bogdan.m14_retrofit.data

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import ru.bogdan.m14_retrofit.domain.User

interface ApiService {
    @GET("me/")
    fun loadUser(): Flow<User>
}