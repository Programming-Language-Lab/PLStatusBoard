package com.pl.data.module

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.pl.data.repository.MemberStatusCacheRepositoryImpl
import com.pl.domain.repository.MemberStatusCacheRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*


@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Provides
    fun provideMemberStatusCacheRepository(dataStore: DataStore<Preferences>): MemberStatusCacheRepository {
        return MemberStatusCacheRepositoryImpl(dataStore)
    }

}