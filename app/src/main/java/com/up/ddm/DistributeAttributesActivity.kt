package com.up.ddm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import PersonagemPack.Personagem
import UtilPack.Escolha

class DistributeAttributesActivity : AppCompatActivity() {

    private lateinit var radioGroupModo: RadioGroup
    private lateinit var rbInserir: RadioButton
    private lateinit var rbAleatorio: RadioButton

    private lateinit var etForca: EditText
    private lateinit var etDestreza: EditText
    private lateinit var etConstituicao: EditText
    private lateinit var etInteligencia: EditText
    private lateinit var etSabedoria: EditText
    private lateinit var etCarisma: EditText

    private lateinit var btnFinalizar: Button

    private lateinit var personagem: Personagem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distribute_attributes)

        radioGroupModo = findViewById(R.id.radioGroupModo)
        rbInserir = findViewById(R.id.rb_inserir)
        rbAleatorio = findViewById(R.id.rb_aleatorio)

        etForca = findViewById(R.id.et_forca)
        etDestreza = findViewById(R.id.et_destreza)
        etConstituicao = findViewById(R.id.et_constituicao)
        etInteligencia = findViewById(R.id.et_inteligencia)
        etSabedoria = findViewById(R.id.et_sabedoria)
        etCarisma = findViewById(R.id.et_carisma)

        btnFinalizar = findViewById(R.id.btn_finalizar)

        // Recuperar o personagem passado
        personagem = intent.getSerializableExtra("personagem") as Personagem

        // Listener para mudar a visibilidade dos campos de entrada
        radioGroupModo.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rb_inserir) {
                setAtributosVisibility(View.VISIBLE)
            } else {
                setAtributosVisibility(View.GONE)
            }
        }

        btnFinalizar.setOnClickListener {
            try {
                if (rbInserir.isChecked) {
                    // Coletar valores dos EditTexts
                    val atributos = mapOf(
                        "forca" to etForca.text.toString().toInt(),
                        "destreza" to etDestreza.text.toString().toInt(),
                        "constituicao" to etConstituicao.text.toString().toInt(),
                        "inteligencia" to etInteligencia.text.toString().toInt(),
                        "sabedoria" to etSabedoria.text.toString().toInt(),
                        "carisma" to etCarisma.text.toString().toInt()
                    )
                    Escolha.escolhaAtributos(personagem, atributos, 1)
                } else if (rbAleatorio.isChecked) {
                    Escolha.escolhaAtributos(personagem, emptyMap(), 2)
                } else {
                    Toast.makeText(this, "Por favor, selecione um modo de distribuição.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Passar o personagem para a próxima activity
                val intent = Intent(this, DisplayCharacterActivity::class.java)
                intent.putExtra("personagem", personagem)
                startActivity(intent)

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setAtributosVisibility(visibility: Int) {
        etForca.visibility = visibility
        etDestreza.visibility = visibility
        etConstituicao.visibility = visibility
        etInteligencia.visibility = visibility
        etSabedoria.visibility = visibility
        etCarisma.visibility = visibility
    }
}