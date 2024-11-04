package com.up.ddm

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import PersonagemPack.Personagem
import UtilPack.Escolha

class CreateCharacterActivity : AppCompatActivity() {

    private lateinit var etNome: EditText
    private lateinit var spinnerRaca: Spinner
    private lateinit var spinnerClasse: Spinner
    private lateinit var btnContinuar: Button

    private val racas = listOf(
        "Anão", "Anão da Colina", "Anão da Montanha", "Draconato", "Drow",
        "Alto-elfo", "Elfo", "Elfo da floresta", "Meio-elfo", "Gnomo",
        "Gnomo da floresta", "Gnomo das rochas", "Halfling", "Halfling pés-leves",
        "Halfling robusto", "Humano", "Meio-orc", "Tiefling"
    )

    private val classes = listOf(
        "Arqueiro", "Artífice", "Bárbaro", "Bardo", "Clérigo", "Druída",
        "Feiticeiro", "Guardião", "Guerreiro", "Ladino", "Mago", "Monge"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_character)

        etNome = findViewById(R.id.et_nome)
        spinnerRaca = findViewById(R.id.spinner_raca)
        spinnerClasse = findViewById(R.id.spinner_classe)
        btnContinuar = findViewById(R.id.btn_continuar)

        // Configurar Spinners
        val racaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, racas)
        racaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRaca.adapter = racaAdapter

        val classeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, classes)
        classeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerClasse.adapter = classeAdapter

        btnContinuar.setOnClickListener {
            val nome = etNome.text.toString().trim()
            val racaSelecionada = spinnerRaca.selectedItemPosition + 1 // Considerando que a lista começa em 1
            val classeSelecionada = spinnerClasse.selectedItemPosition + 1

            if (nome.isEmpty()) {
                Toast.makeText(this, "Por favor, insira um nome.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val personagem = Personagem()
                Escolha.escolhaNome(personagem, nome)
                Escolha.escolhaRaca(personagem, racaSelecionada)
                Escolha.escolhaClasse(personagem, classeSelecionada)

                // Passar o personagem para a próxima activity
                val intent = Intent(this, DistributeAttributesActivity::class.java)
                intent.putExtra("personagem", personagem)
                startActivity(intent)

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}