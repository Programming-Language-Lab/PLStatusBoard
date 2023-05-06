package com.pl.domain.repository

interface MemberStatusCacheRepository {

    suspend fun readMemberStateFromCache(name: String): String

    suspend fun writeMemberStateToCache(name: String, status: String)
}