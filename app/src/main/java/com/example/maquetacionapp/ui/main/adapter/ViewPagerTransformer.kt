package com.example.maquetacionapp.ui.main.adapter

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.maquetacionapp.commons.MIN_ALPHA_TRANSFORMER
import com.example.maquetacionapp.commons.MIN_SCALE_TRANSFORMER

class ViewPagerTransformer  : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < -1 -> { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    alpha = 0f
                }
                position <= 1 -> { // [-1,1]
                    // Modify the default slide transition to shrink the page as well.
                    val scaleFactor = Math.max(MIN_SCALE_TRANSFORMER, 1 - Math.abs(position))
                    val vertMargin = pageHeight * (1 - scaleFactor) / 2
                    val horzMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horzMargin - vertMargin / 2
                    } else {
                        horzMargin + vertMargin / 2
                    }

                    // Scale the page down (between MIN_SCALE and 1).
                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    // Fade the page relative to its size.
                    alpha = (MIN_ALPHA_TRANSFORMER +
                            (((scaleFactor - MIN_SCALE_TRANSFORMER) / (1 - MIN_SCALE_TRANSFORMER)) * (1 - MIN_ALPHA_TRANSFORMER)))
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0f
                }
            }
        }
    }
}