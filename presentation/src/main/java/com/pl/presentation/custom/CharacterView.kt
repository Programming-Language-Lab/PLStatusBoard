package com.pl.presentation.custom

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.pl.presentation.R
import com.pl.presentation.heightRange
import com.pl.presentation.makeRectangle
import com.pl.presentation.toThreeFifth
import com.pl.presentation.widthRange

class CharacterView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {

    private val selfView = this
    private var conflictedView: View? = null
    private lateinit var shakyAnimator: ObjectAnimator
    private lateinit var onSelectedListener: (View) -> Unit

    private val transparentBackground = ContextCompat.getColor(context, android.R.color.transparent)
    private val conflictingBackground = context.getColor(R.color.conflicting_background)
    val characterName: String
    private val targetViews = mutableListOf<View>()

    private var dx = 0f
    private var dy = 0f
    private var initX = 0f
    private var initY = 0f

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CharacterView,
            0, 0).apply {

            try {
                characterName = getString(R.styleable.CharacterView_characterName) ?: ""
            } finally {
                recycle()
            }
        }

        setShakyAnimation()
    }

    fun setTargetView(view: View) {
        targetViews.add(view)
    }

    fun setTargetViews(views: Sequence<View>) {
        targetViews.addAll(views)
    }

    fun setOnSelectedListener(block: (View) -> Unit) {
        onSelectedListener = block
    }

    private fun setShakyAnimation() {
        shakyAnimator = ObjectAnimator.ofFloat(selfView, View.ROTATION, -15f, 15f).apply {
            repeatCount = SHAKY_COUNT
            repeatMode = ObjectAnimator.REVERSE
            duration = SHAKY_DURATION
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    ObjectAnimator.ofFloat(selfView, View.ROTATION, 0f, 0f).start()
                }
            })
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        initX = x
        initY = y
        super.onLayout(changed, left, top, right, bottom)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                dx = selfView.x - event.rawX
                dy = selfView.y - event.rawY
                shakyAnimator.start()
            }
            MotionEvent.ACTION_MOVE -> {

                moveView(event.rawX + dx, event.rawY + dy)

                var isOverlap = false

                targetViews.forEach { view ->

                    if (view != selfView) {

                        val selfRec = makeRectangle(
                            this.widthRange().toThreeFifth(),
                            this.heightRange()
                        )

                        val otherRec = makeRectangle(view.widthRange(), view.heightRange())

                        if (selfRec.checkOverlap(otherRec)) {

                            isOverlap = true

                            // 이미 겹치는 뷰가 있을 경우 초기화 후 할당
                            if (conflictedView != null) {
                                initConflictedView()
                            }

                            setConflictedView(view)
                        }

                    }
                }

                // 겹치는 뷰가 없다면 초기화
                if (isOverlap.not()) {
                    initConflictedView()
                }

            }
            MotionEvent.ACTION_UP -> {
                shakyAnimator.end()

                conflictedView?.let {
                    onSelectedListener(it)
                    initConflictedView()
                }

                resetViewPosition()
                performClick()
            }
        }
        return true
    }

    private fun moveView(x: Float, y: Float) {
        selfView.x = x
        selfView.y = y
    }

    private fun resetViewPosition() {
        selfView.x = initX
        selfView.y = initY
    }

    private fun initConflictedView() {
        conflictedView?.setBackgroundColor(transparentBackground)
        conflictedView = null
    }

    private fun setConflictedView(view: View) {
        conflictedView = view
        conflictedView?.setBackgroundColor(conflictingBackground)
    }

    override fun getAccessibilityClassName(): CharSequence {
        return CLASS_NAME
    }

    companion object {
        private const val SHAKY_COUNT = 100
        private const val SHAKY_DURATION = 500L
        private const val CLASS_NAME = "CharacterView"
    }

}
