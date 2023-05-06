package com.pl.mobile_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pl.domain.MemberInfo
import com.pl.domain.MemberState
import com.pl.domain.MemberStatus
import com.pl.mobile_app.ui.theme.PLStatusBoardTheme
import com.pl.mobile_app.viewmodel.MainModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PLStatusBoardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

    val viewModel = hiltViewModel<MainModel>()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MemberInfo.allKorean().forEach { koreanName ->

            item {
                Text(text = koreanName)
            }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    MemberStatus.allNormalStatus().forEach { memberStatus ->
                        item {
                            SimpleStateButton(memberStatus) {
                                viewModel.postMemberState(
                                    MemberState(
                                        koreanName,
                                        memberStatus
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SimpleStateButton(
    memberStatus: MemberStatus,
    onClicked: () -> Unit = {},
) {
    Button(onClick = { onClicked() }) {
        Text(text = memberStatus.text)
    }
}
