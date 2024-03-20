package ru.bogdan.m14_retrofit.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import ru.bogdan.m14_retrofit.domain.SimpleUser
import ru.bogdan.m14_retrofit.domain.User

class ApiHelperImpl:ApiHelper {

    private val stateEvent:MutableStateFlow<Unit> = MutableStateFlow(Unit)
    private val mapper = UserMapper()
    override fun getUser(): Flow<SimpleUser> {
        return flow{
            emit( mapper.getSimpleUserFromUser(  ApiFactory.apiService.loadUser()))
            stateEvent.collect{
                emit(mapper.getSimpleUserFromUser(  ApiFactory.apiService.loadUser()))
            }
        }
    }

    override fun updateData() {
        stateEvent.value = Unit
    }
}