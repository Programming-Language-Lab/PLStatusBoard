package com.pl.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.children
import com.pl.domain.MemberState
import com.pl.domain.MemberStatus
import com.pl.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by viewModels<MainViewModel>()

    private val statusViews by lazy { binding.statusContainer.children.map {
        (it as FrameLayout).children.first() as TextView
    } }

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
                        viewModel.postMemberState(
                            MemberState(characterView.characterName,
                                MemberStatus.values().first { it.text == statusView.text })
                        )
                    }
                }
            }
        }
    }

    private fun observeStatus() {

        repeatWhenUiStarted {
            viewModel.jihoonOck.collect {
                with(binding) {
                    jihoonOckFireworkLottie.playAnimation()
                    jihoonOckState.setBackgroundResource(it.status.toResource())
                }
            }
        }

        repeatWhenUiStarted {
            viewModel.goeun.collect {
                with(binding) {
                    goeunFireworkLottie.playAnimation()
                    goeunState.setBackgroundResource(it.status.toResource())
                }
            }
        }

        repeatWhenUiStarted {
            viewModel.dojin.collect {
                with(binding) {
                    dojinFireworkLottie.playAnimation()
                    dojinState.setBackgroundResource(it.status.toResource())
                }
            }
        }

        repeatWhenUiStarted {
            viewModel.jihoonLee.collect {
                with(binding) {
                    jihoonLeeFireworkLottie.playAnimation()
                    jihoonLeeState.setBackgroundResource(it.status.toResource())
                }
            }
        }

        repeatWhenUiStarted {
            viewModel.hyunsoo.collect {
                with(binding) {
                    hyunsooFireworkLottie.playAnimation()
                    hyunsooState.setBackgroundResource(it.status.toResource())
                }
            }
        }

        repeatWhenUiStarted {
            viewModel.haesung.collect {
                with(binding) {
                    haesungFireworkLottie.playAnimation()
                    haesungState.setBackgroundResource(it.status.toResource())
                }
            }
        }

        repeatWhenUiStarted {
            viewModel.juyeon.collect {
                with(binding) {
                    juyeonFireworkLottie.playAnimation()
                    juyeonState.setBackgroundResource(it.status.toResource())
                }
            }
        }

        repeatWhenUiStarted {
            viewModel.changwoo.collect {
                with(binding) {
                    changwooFireworkLottie.playAnimation()
                    changwooState.setBackgroundResource(it.status.toResource())
                }
            }
        }

        repeatWhenUiStarted {
            viewModel.yoojin.collect {
                with(binding) {
                    yoojinFireworkLottie.playAnimation()
                    yoojinState.setBackgroundResource(it.status.toResource())
                }
            }
        }

    }
}