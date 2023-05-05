package com.pl.mobile_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pl.domain.MemberState
import com.pl.domain.usecase.SetMemberStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainModel @Inject constructor(
    private val setMemberStateUseCase: SetMemberStateUseCase
): ViewModel() {

    fun postMemberState(memberState: MemberState) {
        viewModelScope.launch {
            setMemberStateUseCase(memberState)
        }
    }
}