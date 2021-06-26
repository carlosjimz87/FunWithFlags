package com.carlosjimz87.funwithflags.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.carlosjimz87.funwithflags.MainActivity
import com.carlosjimz87.funwithflags.R


class SplashActivity : AppCompatActivity() {
    private lateinit var activity: SplashActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        activity = this
        val motionLayout = findViewById<MotionLayout>(R.id.motionLayout)

        motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {

                startActivity(Intent(baseContext, MainActivity::class.java))
                finish()
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }

        })

    }

    override fun onBackPressed() {

    }
}
