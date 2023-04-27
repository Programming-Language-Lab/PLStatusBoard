package com.pl.presentation

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

data class Rectangle(val x1: Float, val y1: Float, val x2: Float, val y2: Float) {
    fun checkOverlap(other: Rectangle): Boolean {
        // 사각형이 겹치지 않는 경우
        if (this.x1 >= other.x2 || this.x2 <= other.x1) return false
        if (this.y1 >= other.y2 || this.y2 <= other.y1) return false

        // 사각형이 겹치는 경우
        return true
    }
}

val View.widthRange: () -> ClosedFloatingPointRange<Float>
    get() = {
            val x = getAbsolutePositionOnScreen().first
            (x..x + width.toFloat())
        }

val View.heightRange: () -> ClosedFloatingPointRange<Float>
    get() = {
            val y = getAbsolutePositionOnScreen().second
            (y..y + height.toFloat())
        }

fun ClosedFloatingPointRange<Float>.toThreeFifth(): ClosedFloatingPointRange<Float> {
    val mid = (start + endInclusive) / 2
    return ((start + mid) / 2 .. (mid + endInclusive) / 2)
}

fun makeRectangle(widthRange: ClosedFloatingPointRange<Float>, heightRange: ClosedFloatingPointRange<Float>): Rectangle {
    return Rectangle(widthRange.start, heightRange.start, widthRange.endInclusive, heightRange.endInclusive)
}

fun View.getAbsolutePositionOnScreen(): Pair<Float, Float> {
    val location = IntArray(2)
    getLocationOnScreen(location)
    return Pair(location[0].toFloat(), location[1].toFloat())
}

fun LifecycleOwner.repeatWhenUiStarted(block: suspend () -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            block.invoke()
        }
    }
}