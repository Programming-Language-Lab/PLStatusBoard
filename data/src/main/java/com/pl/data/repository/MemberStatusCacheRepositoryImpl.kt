package com.pl.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.pl.domain.repository.MemberStatusCacheRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MemberStatusCacheRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): MemberStatusCacheRepository {

    override suspend fun readMemberStateFromCache(name: String): String {
        val nameKey = stringPreferencesKey(name)
        return dataStore.data.map {
            it[nameKey] ?: "error"
        }.first()
    }

    override suspend fun writeMemberStateToCache(name: String, status: String) {
        val nameKey = stringPreferencesKey(name)
        dataStore.edit {
            it[nameKey] = status
        }
    }
}