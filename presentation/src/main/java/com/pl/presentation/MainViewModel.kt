package com.pl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pl.domain.MemberState
import com.pl.domain.MemberStatus
import com.pl.domain.WebHookMessage
import com.pl.domain.usecase.PostWebhookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val postWebhookUseCase: PostWebhookUseCase
) : ViewModel() {

    private var _jihoonOck: MutableStateFlow<MemberState> =
        MutableStateFlow(MemberState.init("옥지훈"))
    val jihoonOck: StateFlow<MemberState>
        get() = _jihoonOck

    private var _goeun: MutableStateFlow<MemberState> = MutableStateFlow(MemberState.init("고은"))
    val goeun: StateFlow<MemberState>
        get() = _goeun

    private var _dojin: MutableStateFlow<MemberState> = MutableStateFlow(MemberState.init("도진"))
    val dojin: StateFlow<MemberState>
        get() = _dojin

    private var _jihoonLee: MutableStateFlow<MemberState> =
        MutableStateFlow(MemberState.init("이지훈"))
    val jihoonLee: StateFlow<MemberState>
        get() = _jihoonLee

    private var _hyunsoo: MutableStateFlow<MemberState> = MutableStateFlow(MemberState.init("현수"))
    val hyunsoo: StateFlow<MemberState>
        get() = _hyunsoo

    private var _haesung: MutableStateFlow<MemberState> = MutableStateFlow(MemberState.init("혜성"))
    val haesung: StateFlow<MemberState>
        get() = _haesung

    private var _juyeon: MutableStateFlow<MemberState> = MutableStateFlow(MemberState.init("주연"))
    val juyeon: StateFlow<MemberState>
        get() = _juyeon

    private var _changwoo: MutableStateFlow<MemberState> = MutableStateFlow(MemberState.init("창우"))
    val changwoo: StateFlow<MemberState>
        get() = _changwoo

    private var _yoojin: MutableStateFlow<MemberState> = MutableStateFlow(MemberState.init("유진"))
    val yoojin: StateFlow<MemberState>
        get() = _yoojin

    fun postWebhookMessage(memberState: MemberState) {
        viewModelScope.launch {
            postWebhookUseCase.invoke(WebHookMessage("*${memberState.name}* 은/는 *${memberState.status.text}*"))
        }
    }

    fun setMemberState(memberName: String, memberStatus: MemberStatus) {
        when (memberName) {
            "옥지훈" -> setJihoonOckMemberState(memberStatus)
            "고은" -> setGoeunMemberState(memberStatus)
            "도진" -> setDojinMemberState(memberStatus)
            "이지훈" -> setJihoonLeeMemberState(memberStatus)
            "현수" -> setHyunsooMemberState(memberStatus)
            "혜성" -> setHaesungMemberState(memberStatus)
            "주연" -> setJuyeonMemberState(memberStatus)
            "창우" -> setChangwooMemberState(memberStatus)
            "유진" -> setYoojinMemberState(memberStatus)
        }

    }

    private fun setJihoonOckMemberState(memberStatus: MemberStatus) {
        _jihoonOck.value = _jihoonOck.value.copy(status = memberStatus)
    }

    private fun setGoeunMemberState(memberStatus: MemberStatus) {
        _goeun.value = _goeun.value.copy(status = memberStatus)
    }

    private fun setDojinMemberState(memberStatus: MemberStatus) {
        _dojin.value = _dojin.value.copy(status = memberStatus)
    }

    private fun setJihoonLeeMemberState(memberStatus: MemberStatus) {
        _jihoonLee.value = _jihoonLee.value.copy(status = memberStatus)
    }

    private fun setHyunsooMemberState(memberStatus: MemberStatus) {
        _hyunsoo.value = _hyunsoo.value.copy(status = memberStatus)
    }

    private fun setHaesungMemberState(memberStatus: MemberStatus) {
        _haesung.value = _haesung.value.copy(status = memberStatus)
    }

    private fun setJuyeonMemberState(memberStatus: MemberStatus) {
        _juyeon.value = _juyeon.value.copy(status = memberStatus)
    }

    private fun setChangwooMemberState(memberStatus: MemberStatus) {
        _changwoo.value = _changwoo.value.copy(status = memberStatus)
    }

    private fun setYoojinMemberState(memberStatus: MemberStatus) {
        _yoojin.value = _yoojin.value.copy(status = memberStatus)
    }

}