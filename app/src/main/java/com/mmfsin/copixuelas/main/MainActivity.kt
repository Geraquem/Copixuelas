package com.mmfsin.copixuelas.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.instructions.IFragmentComunication
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.averquepasa.AVQPFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, IFragmentComunication {

    private val presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.showIntroPhrase()

        button_avqp.setOnClickListener { openFragment(AVQPFragment(this)) }
        button_moneda.setOnClickListener { }
        button_quepreferirias.setOnClickListener { }
        button_maletin.setOnClickListener { }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun showIntroDialog() {

    }

    override fun showIntroPhrase(phrase: String) {
        introPhrase.text = phrase
    }

    override fun closeFragment() {
        supportFragmentManager.popBackStack()
    }

    override fun showFragmentInstructions(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.instructionContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}