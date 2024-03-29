package com.mmfsin.copixuelas.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.firebase.messaging.FirebaseMessaging
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var mInterstitialAd: InterstitialAd? = null

    var showWarningDialog = true
    var firstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(500)
        setTheme(R.style.Theme_Copixuelas)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
        loadInterstitial(AdRequest.Builder().build())
    }

    fun bannerVisible(isVisible: Boolean = true) {
        binding.adView.isVisible = isVisible
        binding.frameBanner.isVisible = isVisible
    }

    fun setAdViewBackGroundColor(color: Int) =
        binding.frameBanner.setBackgroundColor(ContextCompat.getColor(this, color))

    private fun loadInterstitial(adRequest: AdRequest) {
        InterstitialAd.load(
            this,
            getString(R.string.ads_intersticial),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                    loadInterstitial(AdRequest.Builder().build())
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                }
            })
    }

    fun showInterstitial() {
        mInterstitialAd?.let {
            it.show(this)
            loadInterstitial(AdRequest.Builder().build())
        }
    }
}