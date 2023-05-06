package com.pl.domain.usecase

import com.pl.domain.MemberState
import com.pl.domain.repository.MemberStatusCacheRepository
import javax.inject.Inject

class SetMemberStateToCacheUseCase @Inject constructor(
    private val memberStatusCacheRepository: MemberStatusCacheRepository
) {

    suspend operator fun invoke(memberState: MemberState) =
        memberStatusCacheRepository.writeMemberStateToCache(
            memberState.name,
            memberState.status.text
        )
}