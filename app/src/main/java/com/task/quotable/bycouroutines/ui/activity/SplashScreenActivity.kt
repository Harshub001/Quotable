package com.task.quotable.bycouroutines.ui.activity


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.task.quotable.R
import kotlinx.android.synthetic.main.activity_splash.*


@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val quotableImage: ImageView = quotable_icon
        val quotableLogoAnimation = AnimationUtils.loadAnimation(this, R.anim.quotable_icon_anim)
        quotableImage.startAnimation(quotableLogoAnimation)

        val quotableTxt: TextView = quotable_txt
        val quotableTxtAnimation = AnimationUtils.loadAnimation(this, R.anim.quotable_txt_anim)
        quotableTxt.startAnimation(quotableTxtAnimation)
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
            finish()
        }, 2500)
    }
}