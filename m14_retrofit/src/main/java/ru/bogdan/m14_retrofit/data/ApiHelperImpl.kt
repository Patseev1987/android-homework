package ru.bogdan.m14_retrofit.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import ru.bogdan.m14_retrofit.domain.SimpleUser
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val mapper: UserMapper,
    private val apiService: ApiService,
    private val flow: MutableSharedFlow<Unit>
) : ApiHelper {



    override fun getUser(): Flow<SimpleUser> {
        return flow {
            emit(mapper.getSimpleUserFromUser(apiService.loadUser()))
            flow.collect {
                emit(mapper.getSimpleUserFromUser(apiService.loadUser()))
            }
        }
    }

    override suspend fun updateData() {
        flow.emit(Unit)
    }
}