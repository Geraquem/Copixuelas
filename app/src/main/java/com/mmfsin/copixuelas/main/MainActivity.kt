package com.mmfsin.copixuelas.main

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mmfsin.copixuelas.R
import com.mmfsin.copixuelas.averquepasa.AVQPFragment
import com.mmfsin.copixuelas.instructions.IFragmentCommunication
import com.mmfsin.copixuelas.instructions.InstructionsFragment
import com.mmfsin.copixuelas.maletin.MaletinFragment
import com.mmfsin.copixuelas.moneda.MonedaFragment
import com.mmfsin.copixuelas.queprefieres.QuePrefieresFragment
import com.mmfsin.copixuelas.removeLinksUnderline
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView, IFragmentCommunication {

    private val presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.showDialog(this)

        presenter.showIntroPhrase()

        button_avqp.setOnClickListener { openFragment(AVQPFragment(this)) }
        button_moneda.setOnClickListener { openFragment(MonedaFragment(this)) }
        button_quepreferirias.setOnClickListener { openFragment(QuePrefieresFragment()) }
        button_maletin.setOnClickListener { openFragment(MaletinFragment(this)) }
        moreGames.movementMethod = LinkMovementMethod.getInstance()
        moreGames.removeLinksUnderline()
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

    override fun showFragmentInstructions(listener: IFragmentCommunication, id: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.instructionContainer, InstructionsFragment(listener, id))
            .addToBackStack(null)
            .commit()
    }
}