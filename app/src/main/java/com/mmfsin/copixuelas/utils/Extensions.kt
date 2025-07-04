package com.mmfsin.copixuelas.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.CountDownTimer
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.URLSpan
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.mmfsin.copixuelas.base.dialog.ErrorDialog

fun TextView.removeLinksUnderline() {
    val spannable = SpannableString(text)
    for (u in spannable.getSpans(0, spannable.length, URLSpan::class.java)) {
        spannable.setSpan(object : URLSpan(u.url) {
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }, spannable.getSpanStart(u), spannable.getSpanEnd(u), 0)
    }
    text = spannable
}

fun FragmentActivity.showErrorDialog(action: () -> Unit) {
    val dialog = ErrorDialog(action)
    this.let { dialog.show(it.supportFragmentManager, "") }
}

fun countDown(millis: Long, action: () -> Unit) {
    object : CountDownTimer(millis, 1000) {
        override fun onTick(millisUntilFinished: Long) {}
        override fun onFinish() {
            action()
        }
    }.start()
}

fun View.animateY(pos: Float, duration: Long) =
    this.animate().translationY(pos).setDuration(duration)

fun View.animateX(pos: Float, duration: Long) =
    this.animate().translationX(pos).setDuration(duration)

fun View.flipCoin(action: () -> Unit) {
    this.animate().apply {
        duration = 1500
        rotationYBy(5040f)
    }.withEndAction {
        action()
    }.start()
}

fun View.spinTheBottle(spins: Float, duration: Long, lastRotation: Float, onEnd: () -> Unit) {
    val rotationAnimator = ObjectAnimator.ofFloat(this, View.ROTATION, lastRotation, spins)
    rotationAnimator.duration = duration
    rotationAnimator.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            onEnd()
        }
    })
    rotationAnimator.start()
}