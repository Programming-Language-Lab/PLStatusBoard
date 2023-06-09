package com.pl.domain

enum class MemberStatus(val text: String) {
    INIT("초기화"),
    IN("재실"),
    OUT("외출"),
    CLASS("수업"),
    HOME("귀가"),
    ERROR("에러");

    companion object {
        fun toEnum(text: String): MemberStatus {
            return MemberStatus.values().find { it.text == text } ?: ERROR
        }

        fun allNormalStatus(): List<MemberStatus> {
            return listOf(IN, OUT, CLASS, HOME)
        }
    }
}