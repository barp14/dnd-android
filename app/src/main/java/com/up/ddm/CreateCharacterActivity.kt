package com.up.ddm

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import PersonagemPack.Personagem
import UtilPack.Escolha
import android.annotation.SuppressLint
import android.util.Log
import androidx.room.Room
import com.up.ddm.data.PersonagemDao
import com.up.ddm.data.PersonagemDatabase

class CreateCharacterActivity : AppCompatActivity() {
  lateinit var personagemDao: PersonagemDao

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

  @SuppressLint("MissingInflatedId")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_create_character)

    val database = Room.databaseBuilder(
      this,
      PersonagemDatabase::class.java,
      "rpg_database" // Nome da sua base de dados
    ).build()

    personagemDao = database.personagemDao()

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
      val racaSelecionada = spinnerRaca.selectedItemPosition + 1
      val classeSelecionada = spinnerClasse.selectedItemPosition + 1

      if (nome.isEmpty()) {
        Toast.makeText(this, "Por favor, insira um nome.", Toast.LENGTH_SHORT).show()
        return@setOnClickListener
      }

      try {
        val personagem = Personagem().apply {
          Escolha.escolhaNome(this, nome)
          Escolha.escolhaRaca(this, racaSelecionada)
          Escolha.escolhaClasse(this, classeSelecionada)
        }

        // Passa o personagem para a próxima atividade
        val intent = Intent(this, DistributeAttributesActivity::class.java)
        intent.putExtra("personagem", personagem)
        startActivity(intent)

      } catch (e: Exception) {
        Log.e("MeuApp", "Erro ao criar personagem: ${e.message}", e)
        Toast.makeText(this, "Não foi possível criar o personagem.", Toast.LENGTH_LONG).show()
      }
    }
    val btnVerPersonagens = findViewById<Button>(R.id.btn_admin)

    btnVerPersonagens.setOnClickListener {
      val intent = Intent(this, ListCharactersActivity::class.java)
      startActivity(intent)
    }
  }
}
