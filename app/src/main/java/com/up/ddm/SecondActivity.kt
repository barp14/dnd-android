package com.up.ddm

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.up.ddm.models.Raca
import com.up.ddm.models.Classe

class SecondActivity : AppCompatActivity() {
    private lateinit var spinnerRaca: Spinner
    private lateinit var spinnerClasse: Spinner
    private lateinit var buttonProsseguir: Button
    private var racaSelecionada: Raca? = null
    private var classeSelecionada: Classe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        spinnerRaca = findViewById(R.id.spinnerRaca)
        spinnerClasse = findViewById(R.id.spinnerClasse)
        buttonProsseguir = findViewById(R.id.buttonProsseguir)

        setupSpinners()
        setupButton()
    }

    private fun setupSpinners() {
        val racas = Raca.values().map { it.nome }
        val classes = Classe.values().map { it.nome }

        val adapterRaca = ArrayAdapter(this, android.R.layout.simple_spinner_item, racas)
        adapterRaca.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRaca.adapter = adapterRaca

        val adapterClasse = ArrayAdapter(this, android.R.layout.simple_spinner_item, classes)
        adapterClasse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerClasse.adapter = adapterClasse

        spinnerRaca.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                racaSelecionada = Raca.values()[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                racaSelecionada = null
            }
        }

        spinnerClasse.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                classeSelecionada = Classe.values()[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                classeSelecionada = null
            }
        }
    }

    private fun setupButton() {
        buttonProsseguir.setOnClickListener {
            if (racaSelecionada != null && classeSelecionada != null) {
                // Passa a raça e a classe para a próxima Activity
                val intent = Intent(this, CreateCharacterActivity::class.java)
                intent.putExtra("raca", racaSelecionada?.nome)
                intent.putExtra("classe", classeSelecionada?.nome)
                startActivity(intent)
            }
        }
    }
}
