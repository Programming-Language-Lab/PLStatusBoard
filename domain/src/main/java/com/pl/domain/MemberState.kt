package com.pl.domain

data class MemberState(
    val name: String,
    val status: MemberStatus,
) {
    companion object {
        fun init(name: String) = MemberState(name, MemberStatus.INIT)
        fun error() = MemberState("error", MemberStatus.ERROR)
    }
}
