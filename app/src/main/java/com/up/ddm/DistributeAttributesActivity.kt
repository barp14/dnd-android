package com.up.ddm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import PersonagemPack.Personagem
import UtilPack.Escolha
import android.util.Log
import androidx.room.Room
import com.up.ddm.data.PersonagemDao
import com.up.ddm.data.PersonagemDatabase
import com.up.ddm.data.PersonagemEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DistributeAttributesActivity : AppCompatActivity() {

    private lateinit var personagemDao: PersonagemDao

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

        // Instanciar o DAO aqui
        val database = Room.databaseBuilder(
            applicationContext,
            PersonagemDatabase::class.java,
            "rpg_database"
        ).build()

        personagemDao = database.personagemDao() // Inicializa o personagemDao

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
                    // Coletar valores dos EditTexts e garantir que não sejam nulos
                    val forca = etForca.text.toString().toIntOrNull() ?: 0
                    val destreza = etDestreza.text.toString().toIntOrNull() ?: 0
                    val constituicao = etConstituicao.text.toString().toIntOrNull() ?: 0
                    val inteligencia = etInteligencia.text.toString().toIntOrNull() ?: 0
                    val sabedoria = etSabedoria.text.toString().toIntOrNull() ?: 0
                    val carisma = etCarisma.text.toString().toIntOrNull() ?: 0

                    val atributos = mapOf(
                        "forca" to forca,
                        "destreza" to destreza,
                        "constituicao" to constituicao,
                        "inteligencia" to inteligencia,
                        "sabedoria" to sabedoria,
                        "carisma" to carisma
                    )

                    Escolha.escolhaAtributos(personagem, atributos, 1)
                } else if (rbAleatorio.isChecked) {
                    Escolha.escolhaAtributos(personagem, emptyMap(), 2)
                } else {
                    Toast.makeText(this, "Por favor, selecione um modo de distribuição.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Salvar o personagem no banco de dados após definir os atributos
                val personagemEntity = PersonagemEntity(
                    nome = personagem.nome,
                    classe = personagem.classe.toString(),
                    raca = personagem.raca.toString(),
                    carisma = personagem.carisma,
                    sabedoria = personagem.sabedoria,
                    inteligencia = personagem.inteligencia,
                    constituicao = personagem.constituicao,
                    destreza = personagem.destreza,
                    forca = personagem.forca,
                    vida = personagem.vida
                )

                // Certifique-se de que o personagemDao foi inicializado corretamente
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val insertedId = personagemDao.insert(personagemEntity)
                        Log.d("MeuApp", "Personagem inserido com ID: $insertedId")

                        withContext(Dispatchers.Main) {
                            // Passar para a próxima atividade
                            val intent = Intent(this@DistributeAttributesActivity, DisplayCharacterActivity::class.java)
                            intent.putExtra("personagem", personagem)
                            startActivity(intent)
                        }
                    } catch (e: Exception) {
                        Log.e("MeuApp", "Erro ao inserir personagem: ${e.message}", e)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@DistributeAttributesActivity, "Não foi possível salvar o personagem.", Toast.LENGTH_LONG).show()
                        }
                    }
                }

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

