package ru.bogdan.m14_retrofit.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.bogdan.m14_retrofit.domain.SimpleUser
import ru.bogdan.m14_retrofit.domain.User

class ApiHelperImpl : ApiHelper {

    private val flow: MutableSharedFlow<Unit> = MutableSharedFlow()
    private val mapper = UserMapper()
    override fun getUser(): Flow<SimpleUser> {
        return flow {
            emit(mapper.getSimpleUserFromUser(ApiFactory.apiService.loadUser()))
            flow.collect {
                emit(mapper.getSimpleUserFromUser(ApiFactory.apiService.loadUser()))
            }
        }
    }

    override suspend fun updateData() {
        flow.emit(Unit)
    }
}