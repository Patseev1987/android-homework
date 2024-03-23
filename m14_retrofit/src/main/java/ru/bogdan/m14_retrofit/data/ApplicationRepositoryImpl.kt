package ru.bogdan.m14_retrofit.data

import kotlinx.coroutines.flow.Flow
import ru.bogdan.m14_retrofit.data.ApiHelper
import ru.bogdan.m14_retrofit.domain.ApplicationRepository
import ru.bogdan.m14_retrofit.domain.SimpleUser
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper
):ApplicationRepository {
    override fun getUser(): Flow<SimpleUser> {
       return apiHelper.getUser()
    }

    override suspend fun updateUser() {
        apiHelper.updateData()
    }
}