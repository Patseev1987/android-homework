package ru.bogdan.m15_room.domain

import ru.bogdan.m15_room.data.database.WordEntity
import javax.inject.Inject

class CleanTableUseCase @Inject constructor(private val applicationRepository: ApplicationRepository) {
    suspend fun cleanTable() = applicationRepository.clearTable()
}