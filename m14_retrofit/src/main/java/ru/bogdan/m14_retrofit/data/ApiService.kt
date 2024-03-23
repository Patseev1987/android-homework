package ru.bogdan.m14_retrofit.data


import retrofit2.http.GET
import ru.bogdan.m14_retrofit.data.pojo.User

interface ApiService {
    @GET("api")
   suspend fun loadUser(): User
}