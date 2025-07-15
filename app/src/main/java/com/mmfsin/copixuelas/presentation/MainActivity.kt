package com.mmfsin.copixuelas.presentation

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var mInterstitialAd: InterstitialAd? = null

    var firstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(300)
        setTheme(R.style.Theme_Copixuelas)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
        loadInterstitial(AdRequest.Builder().build())
    }

    fun changeStatusBarColor(color: Int, darkIcons: Boolean) {
        // Android 15+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            window.decorView.setOnApplyWindowInsetsListener { view, insets ->
                val statusBarInsets = insets.getInsets(WindowInsets.Type.statusBars())
                view.setBackgroundColor(ContextCompat.getColor(this, color))
                view.setPadding(0, statusBarInsets.top, 0, 0)
                insets
            }

        } else {
            // For Android 14 and below
            @Suppress("DEPRECATION")
            window.statusBarColor = ContextCompat.getColor(this, color)
        }

        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
            darkIcons
    }

    fun bannerVisible(isVisible: Boolean = true) {
        binding.apply {
            adView.isVisible = isVisible
            frameBanner.isVisible = isVisible
        }
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