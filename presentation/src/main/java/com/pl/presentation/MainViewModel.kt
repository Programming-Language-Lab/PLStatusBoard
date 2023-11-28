package com.pl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pl.domain.MemberInfo
import com.pl.domain.MemberState
import com.pl.domain.MemberStatus
import com.pl.domain.WebHookMessage
import com.pl.domain.usecase.CheckDuplicatedMemberStateUseCase
import com.pl.domain.usecase.GetMemberStateFlowUseCase
import com.pl.domain.usecase.PostWebhookUseCase
import com.pl.domain.usecase.SetMemberStateToCacheUseCase
import com.pl.domain.usecase.SetMemberStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val postWebhookUseCase: PostWebhookUseCase,
    private val getMemberStateFlowUseCase: GetMemberStateFlowUseCase,
    private val setMemberStateUseCase: SetMemberStateUseCase,
    private val setMemberStateToCacheUseCase: SetMemberStateToCacheUseCase,
    private val checkDuplicatedMemberStateUseCase: CheckDuplicatedMemberStateUseCase
) : ViewModel() {

    init {
        MemberInfo.allEnglish().forEach { memberName ->
            viewModelScope.launch {
                getMemberStateFlowUseCase.invoke(memberName).collect { memberState ->
                    checkDuplicatedMemberStateUseCase(memberState).let { isDuplicated ->
                        if (isDuplicated.not()) {
                            setMemberStateToCacheUseCase(memberState)
                            postWebhookUseCase.invoke(
                                WebHookMessage(
                                    "*${
                                        MemberInfo.findKoreanByEnglish(
                                            memberName
                                        )
                                    }* 은/는 *${memberState.status.text}*"
                                )
                            )
                        }
                    }


                    setMemberState(memberState)
                }
            }
        }
    }

    private var _jihoonOck: MutableStateFlow<MemberState> =
        MutableStateFlow(MemberState.init("옥지훈"))
    val jihoonOck: StateFlow<MemberState>
        get() = _jihoonOck

    private var _goeun: MutableStateFlow<MemberState> = MutableStateFlow(MemberState.init("고은"))
    val goeun: StateFlow<MemberState>
        get() = _goeun

    private var _seoyeon: MutableStateFlow<MemberState> = MutableStateFlow(MemberState.init("서연"))
    val seoyeon: StateFlow<MemberState>
        get() = _seoyeon

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

    fun postMemberState(newState: MemberState) {
        if (newState.status != MemberStatus.INIT) {
            viewModelScope.launch {
                setMemberStateUseCase.invoke(newState)
            }
        }
    }

    private fun setMemberState(memberState: MemberState) {
        when (memberState.name) {
            MemberInfo.JIHOON_OCK.ko -> setJihoonOckMemberState(memberState.status)
            MemberInfo.GOEUN.ko -> setGoeunMemberState(memberState.status)
            MemberInfo.SEOYEON.ko -> setSeoyeonMemberState(memberState.status)
            MemberInfo.JIHOON_LEE.ko -> setJihoonLeeMemberState(memberState.status)
            MemberInfo.HYUNSOO.ko -> setHyunsooMemberState(memberState.status)
            MemberInfo.HAESUNG.ko -> setHaesungMemberState(memberState.status)
            MemberInfo.JUYEON.ko -> setJuyeonMemberState(memberState.status)
            MemberInfo.CHANGWOO.ko -> setChangwooMemberState(memberState.status)
            MemberInfo.YOOJIN.ko -> setYoojinMemberState(memberState.status)
        }
    }

    private fun setJihoonOckMemberState(memberStatus: MemberStatus) {
        _jihoonOck.value = _jihoonOck.value.copy(status = memberStatus)
    }

    private fun setGoeunMemberState(memberStatus: MemberStatus) {
        _goeun.value = _goeun.value.copy(status = memberStatus)
    }

    private fun setSeoyeonMemberState(memberStatus: MemberStatus) {
        _seoyeon.value = _seoyeon.value.copy(status = memberStatus)
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