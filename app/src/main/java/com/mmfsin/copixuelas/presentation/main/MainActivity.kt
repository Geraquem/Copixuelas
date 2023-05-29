package com.mmfsin.copixuelas.presentation.main

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.firebase.messaging.FirebaseMessaging
import com.mmfsin.copixuelas.domain.interfaces.ICommunication
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.presentation.averquepasa.AVQPFragment
import com.mmfsin.copixuelas.presentation.warning.WarningFragment
import com.mmfsin.copixuelas.presentation.instructions.InstructionsFragment
import com.mmfsin.copixuelas.presentation.maletin.MaletinFragment
import com.mmfsin.copixuelas.presentation.moneda.MonedaFragment
import com.mmfsin.copixuelas.presentation.queprefieres.QuePrefieresFragment
import com.mmfsin.copixuelas.utils.removeLinksUnderline
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, ICommunication {

    private var mInterstitialAd: InterstitialAd? = null

    private val presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        loadInterstitial(AdRequest.Builder().build())

//        getFCMToken()

        openWarningFragment()

        presenter.showIntroPhrase()

        button_avqp.setOnClickListener { openFragment(AVQPFragment(this)) }
        button_moneda.setOnClickListener { openFragment(MonedaFragment(this)) }
        button_quepreferirias.setOnClickListener { openFragment(QuePrefieresFragment(this)) }
        button_maletin.setOnClickListener { openFragment(MaletinFragment(this)) }
        moreGames.movementMethod = LinkMovementMethod.getInstance()
        moreGames.removeLinksUnderline()
    }

    private fun openWarningFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, WarningFragment(this)).addToBackStack(null).commit()
    }

    private fun openFragment(fragment: Fragment) {
        showInterstitial()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null).commit()
    }

    override fun showIntroPhrase(phrase: String) {
        introPhrase.text = phrase
    }

    override fun closeFragment() {
        supportFragmentManager.popBackStack()
    }

    override fun showFragmentInstructions(listener: ICommunication, id: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.instructionContainer, InstructionsFragment(listener, id))
            .addToBackStack(null).commit()
    }

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

    private fun showInterstitial() {
        val rand = (0..6).random()
        if (rand == 1) {
            mInterstitialAd?.let { ad ->
                ad.show(this)
                loadInterstitial(AdRequest.Builder().build())
            }
        }
    }

    override fun showAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd!!.show(this)
            loadInterstitial(AdRequest.Builder().build())
        }
    }

    private fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (it.isSuccessful) Log.i("FCM", it.result)
            else Log.i("FCM", "no token")
        }
    }
}