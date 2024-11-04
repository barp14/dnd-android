package com.up.ddm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.up.ddm.data.PersonagemDao
import com.up.ddm.data.PersonagemDatabase
import com.up.ddm.data.PersonagemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditCharacterActivity : AppCompatActivity() {

  private lateinit var personagemDao: PersonagemDao
  private lateinit var etNome: EditText
  private lateinit var btnSave: Button

  private var personagemId: Long = -1

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_edit_character)

    // Inicializar o DAO
    val database = Room.databaseBuilder(
      applicationContext,
      PersonagemDatabase::class.java,
      "rpg_database"
    ).build()

    personagemDao = database.personagemDao()

    // Inicializar Views
    etNome = findViewById(R.id.et_nome)
    btnSave = findViewById(R.id.btn_save)

    // Receber o ID do personagem a ser editado
    personagemId = intent.getLongExtra("personagem_id", -1)

    // Carregar dados do personagem
    carregarDadosPersonagem()

    // Configurar o botão de salvar
    btnSave.setOnClickListener {
      salvarAlteracoes()
    }
  }

  private fun carregarDadosPersonagem() {
    lifecycleScope.launch {
      // Executar consulta em uma thread de I/O
      val personagem = withContext(Dispatchers.IO) {
        personagemDao.getPersonagemById(personagemId) // Método que você precisa implementar
      }
      personagem?.let {
        etNome.setText(it.nome)
      } ?: run {
        Toast.makeText(this@EditCharacterActivity, "Personagem não encontrado.", Toast.LENGTH_SHORT).show()
        finish() // Finalizar a activity se o personagem não for encontrado
      }
    }
  }

  private fun salvarAlteracoes() {
    val novoNome = etNome.text.toString()

    lifecycleScope.launch {
      try {
        // Buscar o personagem existente em uma thread de I/O
        val personagemExistente = withContext(Dispatchers.IO) {
          personagemDao.getPersonagemById(personagemId)
        }

        if (personagemExistente != null) {
          // Atualizar apenas o nome
          personagemExistente.nome = novoNome
          withContext(Dispatchers.IO) {
            personagemDao.update(personagemExistente) // Atualiza apenas o nome
          }
          Toast.makeText(this@EditCharacterActivity, "Personagem atualizado com sucesso.", Toast.LENGTH_SHORT).show()
          finish() // Finalizar a activity após salvar
        } else {
          Toast.makeText(this@EditCharacterActivity, "Personagem não encontrado para atualização.", Toast.LENGTH_SHORT).show()
        }
      } catch (e: Exception) {
        Toast.makeText(this@EditCharacterActivity, "Erro ao atualizar personagem: ${e.message}", Toast.LENGTH_SHORT).show()
      }
    }
  }
}
