package com.mmfsin.copixuelas.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mmfsin.copixuelas.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.showIntroPhrase()

        button_avqp.setOnClickListener {  }
        button_moneda.setOnClickListener {  }
        button_quepreferirias.setOnClickListener {  }
        button_maletin.setOnClickListener {  }
    }

    override fun showIntroDialog() {
        TODO("Not yet implemented")
    }

    override fun showIntroPhrase(phrase: String) {
        introPhrase.text = phrase
    }
}