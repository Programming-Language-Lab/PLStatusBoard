package com.pl.domain

enum class MemberInfo(val ko: String, val en: String) {
    JIHOON_OCK("옥지훈", "jihoonOck"),
    GOEUN("고은", "goeun"),
    DOJIN("도진", "dojin"),
    JIHOON_LEE("이지훈", "jihoonLee"),
    HYUNSOO("현수", "hyunsoo"),
    HAESUNG("해성", "haesung"),
    JUYEON("주연", "juyeon"),
    CHANGWOO("창우", "changwoo"),
    YOOJIN("유진", "yoojin");

    companion object {
        fun allKorean() = MemberInfo.values().map { it.ko }
        fun allEnglish() = MemberInfo.values().map { it.en }
        fun findKoreanByEnglish(english: String) = MemberInfo.values().find { it.en == english }?.ko ?: ERROR
        fun findEnglishByKorean(korean: String) = MemberInfo.values().find { it.ko == korean }?.en ?: ERROR

        private const val ERROR = "MEMBER_INFO_ERROR"
    }
}