package com.example.sbertrainee.view.fragments.util

import android.animation.Animator

abstract class AnimatorListener : Animator.AnimatorListener {

    override fun onAnimationRepeat(animation: Animator?) =
        Unit

    override fun onAnimationCancel(animation: Animator?) =
        Unit

    override fun onAnimationStart(animation: Animator?) =
        Unit
}