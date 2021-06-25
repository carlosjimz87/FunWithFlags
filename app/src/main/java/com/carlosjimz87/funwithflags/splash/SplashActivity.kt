package com.carlosjimz87.funwithflags.splash

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.carlosjimz87.funwithflags.R

const val DURATION_JSON_ANIM = 1500L
const val DURATION_JSON_DELAY = 3000L

const val DURATION_LOGO_ANIM = 5000L
const val DURATION_LOGO_DELAY = 1000L

const val DURATION_BG_ANIM = 1500L

class SplashActivity : AppCompatActivity() {

    private lateinit var logo: ImageView;
    private lateinit var bgBottom: ImageView;
    private lateinit var bgTop: ImageView;
    private lateinit var lottieAnim: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        logo = findViewById(R.id.logo)
        bgBottom = findViewById(R.id.bg_bottom)
        bgTop = findViewById(R.id.bg_top)
        lottieAnim = findViewById(R.id.lottieAnim)


        // json animation
        lottieAnim.animate().alphaBy(-10f).setDuration(DURATION_JSON_ANIM).startDelay =
            DURATION_JSON_DELAY
        // logo
        logo.animate()
            .alphaBy(10F).setDuration(DURATION_JSON_ANIM)
            .startDelay = DURATION_JSON_DELAY


//        startActivity(Intent(this,MainActivity::class.java))

    }
}
