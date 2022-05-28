package ru.anyfon.pbx.manager.domain.callmanager.cdr.descriptor

import ru.anyfon.pbx.manager.domain.callmanager.cdr.CallRecordRepository

interface CallRecordPersister {

    suspend fun save()

    class Base(
        private val recordParamsBuilder: CallRecordParamsBuilder,
        private val repository: CallRecordRepository
    ) : CallRecordPersister {

        override suspend fun save() = recordParamsBuilder.build().let {
            repository.add(it)
        }
    }
}
