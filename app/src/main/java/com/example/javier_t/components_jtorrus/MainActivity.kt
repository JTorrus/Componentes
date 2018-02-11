package com.example.javier_t.components_jtorrus

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.customsearch.CustomSearchView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CustomSearchView.OnSearchButtonClickedListener{
    override fun onSearchButtonClicked(source: CustomSearchView, currentText: String) {
        Toast.makeText(this, "Buscando con el texto ${currentText}", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customSearchView.setOnSearchButtonClickedListener(this)
    }
}
