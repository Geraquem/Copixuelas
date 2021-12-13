package com.mmfsin.copixuelas.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.averquepasa.AVQPFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.showIntroPhrase()

        button_avqp.setOnClickListener { openFragment(AVQPFragment()) }
        button_moneda.setOnClickListener { }
        button_quepreferirias.setOnClickListener { }
        button_maletin.setOnClickListener { openFragment(AVQPFragment()) }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun showIntroDialog() {
        TODO("Not yet implemented")
    }

    override fun showIntroPhrase(phrase: String) {
        introPhrase.text = phrase
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}