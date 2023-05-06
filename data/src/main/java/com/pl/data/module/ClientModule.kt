package com.pl.data.module

import com.pl.data.StatusBoardClientImpl
import com.pl.domain.StatusBoardClient
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
class ClientModule {

    @Provides
    fun provideClient(): StatusBoardClient {
        return  StatusBoardClientImpl()
    }

}