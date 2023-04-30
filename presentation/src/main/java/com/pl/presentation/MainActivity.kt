package com.pl.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.children
import com.pl.domain.MemberStatus
import com.pl.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<MainViewModel>()

    private val statusViews by lazy { binding.statusContainer.children }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setCharacterViews()
        observeStatus()

    }

    private fun setCharacterViews() {

        val jihoonOckCharacterView = binding.jihoonOckCharacterView
        val goeunCharacterView = binding.goeunCharacterView
        val dojinCharacterView = binding.dojinCharacterView
        val jihoonLeeCharacterView = binding.jihoonLeeCharacterView
        val hyunsooCharacterView = binding.hyunsooCharacterView
        val haesungCharacterView = binding.haesungCharacterView
        val juyeonCharacterView = binding.juyeonCharacterView
        val changwooCharacterView = binding.changwooCharacterView
        val yoojinCharacterView = binding.yoojinCharacterView

        val characterViews = listOf(
            jihoonOckCharacterView,
            goeunCharacterView,
            dojinCharacterView,
            jihoonLeeCharacterView,
            hyunsooCharacterView,
            haesungCharacterView,
            juyeonCharacterView,
            changwooCharacterView,
            yoojinCharacterView
        )

        characterViews.forEach { characterView ->

            characterView.apply {
                setTargetViews(statusViews)
                setOnSelectedListener { statusView ->
                    if (statusView is TextView) {
                        viewModel.setMemberState(
                            characterView.characterName,
                            MemberStatus.values().first { it.text == statusView.text })
                    }

                }
            }

        }
    }

    private fun observeStatus() {

        repeatWhenUiStarted {
            viewModel.jihoonOck.collect {
                binding.jihoonOckState.text = it.status.text
                viewModel.postWebhookMessage(it)
            }
        }

        repeatWhenUiStarted {
            viewModel.goeun.collect {
                binding.goeunState.text = it.status.text
                viewModel.postWebhookMessage(it)
            }
        }

        repeatWhenUiStarted {
            viewModel.dojin.collect {
                binding.dojinState.text = it.status.text
                viewModel.postWebhookMessage(it)
            }
        }

        repeatWhenUiStarted {
            viewModel.jihoonLee.collect {
                binding.jihoonLeeState.text = it.status.text
                viewModel.postWebhookMessage(it)
            }
        }

        repeatWhenUiStarted {
            viewModel.hyunsoo.collect {
                binding.hyunsooState.text = it.status.text
                viewModel.postWebhookMessage(it)
            }
        }

        repeatWhenUiStarted {
            viewModel.haesung.collect {
                binding.haesungState.text = it.status.text
                viewModel.postWebhookMessage(it)
            }
        }

        repeatWhenUiStarted {
            viewModel.juyeon.collect {
                binding.juyeonState.text = it.status.text
                viewModel.postWebhookMessage(it)
            }
        }

        repeatWhenUiStarted {
            viewModel.changwoo.collect {
                binding.changwooState.text = it.status.text
                viewModel.postWebhookMessage(it)
            }
        }

        repeatWhenUiStarted {
            viewModel.yoojin.collect {
                binding.yoojinState.text = it.status.text
                viewModel.postWebhookMessage(it)
            }
        }

    }
}