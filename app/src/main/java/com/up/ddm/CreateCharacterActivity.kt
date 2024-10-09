package com.up.ddm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.up.ddm.models.Raca
import com.up.ddm.models.Classe

class CreateCharacterActivity : AppCompatActivity() {
    private lateinit var editTextNome: EditText
    private lateinit var textViewResumo: TextView
    private lateinit var buttonSalvar: Button

    private lateinit var racaSelecionada: String
    private lateinit var classeSelecionada: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_character)

        racaSelecionada = intent.getStringExtra("raca") ?: ""
        classeSelecionada = intent.getStringExtra("classe") ?: ""

        editTextNome = findViewById(R.id.editTextNome)
        textViewResumo = findViewById(R.id.textViewResumo)
        buttonSalvar = findViewById(R.id.buttonSalvar)

        // Exibir as informações da raça e classe selecionadas
        exibirResumo()

        buttonSalvar.setOnClickListener {
            salvarPersonagem()
        }
    }

    private fun exibirResumo() {
        textViewResumo.text = "Raça: $racaSelecionada\nClasse: $classeSelecionada"
    }

    private fun salvarPersonagem() {
        val nome = editTextNome.text.toString()
        val resumo = """
            Nome: $nome
            Raça: $racaSelecionada
            Classe: $classeSelecionada
        """.trimIndent()

        textViewResumo.text = resumo
    }
}
