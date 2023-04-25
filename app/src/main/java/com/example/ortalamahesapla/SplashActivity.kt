package com.example.ortalamahesapla

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import com.example.ortalamahesapla.databinding.SplashLayoutBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var bindingSplash: SplashLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSplash = SplashLayoutBinding.inflate(layoutInflater)
        val view = bindingSplash.root
        setContentView(view)

        val asagidanGelenButon = AnimationUtils.loadAnimation(this, R.anim.asagidan_gelen_buton)
        bindingSplash.button.animation = asagidanGelenButon

        val yukaridanGelenBalon = AnimationUtils.loadAnimation(this, R.anim.yukaridan_gelen_balon)
        bindingSplash.imageView.animation = yukaridanGelenBalon

        val asagidanGidenButon = AnimationUtils.loadAnimation(this, R.anim.asagidan_giden_buton)
        val yukaridanGidenBalon = AnimationUtils.loadAnimation(this, R.anim.yukaridan_giden_balon)
        bindingSplash.button.setOnClickListener {
            bindingSplash.button.startAnimation(asagidanGidenButon)
            bindingSplash.imageView.startAnimation(yukaridanGidenBalon)
            object : CountDownTimer(1000,1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
            }.start()
        }
    }
}