package com.pl.domain.usecase

import com.pl.domain.repository.MemberStatusCacheRepository
import javax.inject.Inject

class GetMemberStateFromCacheUseCase @Inject constructor(
    private val memberStatusCacheRepository: MemberStatusCacheRepository
) {

    suspend operator fun invoke(name: String) = memberStatusCacheRepository.readMemberStateFromCache(name)
}