package com.example.soccert.utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.soccert.R

class TranslateAnimationUtil(context: Context, viewAnimation: View) : View.OnTouchListener {
    private var gestureDetector: GestureDetector? = null

    init {
        gestureDetector = GestureDetector(context, SimpleGestureDetector(viewAnimation))
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?) =
        gestureDetector?.onTouchEvent(event) ?: false

    class SimpleGestureDetector(private val viewAnimation: View?) :
        GestureDetector.SimpleOnGestureListener() {

        private var isFinishAnimation = true

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (distanceY > 0) hiddenView() else showView()
            return super.onScroll(e1, e2, distanceX, distanceY)
        }

        private fun hiddenView() {
            if (viewAnimation == null || viewAnimation.visibility == View.GONE) {
                return
            }

            val animationDown =
                AnimationUtils.loadAnimation(viewAnimation.context, R.anim.slide_in_bottom)
            animationDown.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    viewAnimation.hide()
                    isFinishAnimation = true
                }

                override fun onAnimationStart(animation: Animation?) {
                    viewAnimation.show()
                    isFinishAnimation = false
                }
            })

            if (isFinishAnimation) viewAnimation.startAnimation(animationDown)
        }

        private fun showView() {
            if (viewAnimation == null || viewAnimation.visibility == View.VISIBLE) {
                return
            }

            val animationUp =
                AnimationUtils.loadAnimation(viewAnimation.context, R.anim.slide_in_top)
            animationUp.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    isFinishAnimation = true
                }

                override fun onAnimationStart(animation: Animation?) {
                    viewAnimation.show()
                    isFinishAnimation = false
                }
            })

            if (isFinishAnimation) viewAnimation.startAnimation(animationUp)
        }
    }
}
