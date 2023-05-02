package com.pl.data.model.firebase

import com.pl.domain.MemberState
import com.pl.domain.MemberStatus

data class MemberStateResponse(
    val name: String = "",
    val status: String = "",
) {
    fun toMemberState(): MemberState {
        return MemberState(
            name = name,
            status = MemberStatus.toEnum(status),
        )
    }
}