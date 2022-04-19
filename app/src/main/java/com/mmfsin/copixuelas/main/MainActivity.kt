package com.mmfsin.copixuelas.main

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.mmfsin.copixuelas.IFragmentCommunication
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.averquepasa.AVQPFragment
import com.mmfsin.copixuelas.aviso.WarningFragment
import com.mmfsin.copixuelas.instructions.InstructionsFragment
import com.mmfsin.copixuelas.maletin.MaletinFragment
import com.mmfsin.copixuelas.moneda.MonedaFragment
import com.mmfsin.copixuelas.queprefieres.QuePrefieresFragment
import com.mmfsin.copixuelas.removeLinksUnderline
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, IFragmentCommunication {

    /******* BANNER (CRTL + SHIFT + R)
     * REAL  ca-app-pub-4515698012373396/5500518392
     * PRUEBAS ca-app-pub-3940256099942544/6300978111

     ******* INSTERTICIAL
     * REAL  ca-app-pub-4515698012373396/3619803975
     * PRUEBAS ca-app-pub-3940256099942544/1033173712
     */

    private var mInterstitialAd: InterstitialAd? = null

    private val presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        loadInterstitial(AdRequest.Builder().build())

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
            .replace(R.id.fragmentContainer, WarningFragment(this))
            .addToBackStack(null)
            .commit()
    }

    private fun openFragment(fragment: Fragment) {
        showIntersticial()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun showIntroPhrase(phrase: String) {
        introPhrase.text = phrase
    }

    override fun closeFragment() {
        supportFragmentManager.popBackStack()
    }

    override fun showFragmentInstructions(listener: IFragmentCommunication, id: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.instructionContainer, InstructionsFragment(listener, id))
            .addToBackStack(null)
            .commit()
    }

    private fun loadInterstitial(adRequest: AdRequest) {
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
                loadInterstitial(AdRequest.Builder().build())
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })
    }

    private fun showIntersticial() {
        val rand = (0..6).random()
        if (rand == 1 && mInterstitialAd != null) {
            mInterstitialAd!!.show(this)
            loadInterstitial(AdRequest.Builder().build())
        }
    }

    override fun showAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd!!.show(this)
            loadInterstitial(AdRequest.Builder().build())
        }
    }
}