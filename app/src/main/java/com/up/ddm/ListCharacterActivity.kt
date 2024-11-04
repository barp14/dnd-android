package com.up.ddm

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.up.ddm.data.PersonagemDao
import com.up.ddm.data.PersonagemDatabase
import com.up.ddm.data.PersonagemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListCharactersActivity : AppCompatActivity() {

  private lateinit var personagemDao: PersonagemDao
  private lateinit var listViewPersonagens: ListView
  private lateinit var adapter: PersonagemAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_list_characters)

    // Inicializar o DAO
    val database = Room.databaseBuilder(
      applicationContext,
      PersonagemDatabase::class.java,
      "rpg_database"
    ).build()

    personagemDao = database.personagemDao()

    listViewPersonagens = findViewById(R.id.list_view_personagens)
    adapter = PersonagemAdapter(this) // Criar o adapter
    listViewPersonagens.adapter = adapter // Definir o adapter para a ListView

    // Carregar a lista de personagens na Activity
    carregarListaPersonagens()

    listViewPersonagens.setOnItemClickListener { parent, view, position, id ->
      val personagem = adapter.getItem(position) as PersonagemEntity

      // Exibir detalhes do personagem (opcional)
      Toast.makeText(this, "Personagem selecionado: ${personagem.nome}", Toast.LENGTH_SHORT).show()
    }
  }

  private fun carregarListaPersonagens() {
    lifecycleScope.launch {
      val personagens = withContext(Dispatchers.IO) {
        personagemDao.getAllPersonagens()
      }
      adapter.personagens = personagens.toMutableList() // Atualizar a lista do adapter
      adapter.notifyDataSetChanged() // Notificar o adapter para atualizar a lista
    }
  }

  inner class PersonagemAdapter(context: Context) : BaseAdapter() {

    var personagens: MutableList<PersonagemEntity> = mutableListOf()

    override fun getCount(): Int {
      return personagens.size
    }

    override fun getItem(position: Int): Any {
      return personagens[position]
    }

    override fun getItemId(position: Int): Long {
      return personagens[position].id // Retorna o ID do personagem
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
      val personagem = personagens[position]
      val view: View = convertView ?: layoutInflater.inflate(R.layout.item_personagem, parent, false)

      val tvNome = view.findViewById<TextView>(R.id.tv_nome)
      val tvClasseRaca = view.findViewById<TextView>(R.id.tv_classe_raca)
      val btnDelete = view.findViewById<Button>(R.id.btn_delete)
      val btnEdit = view.findViewById<Button>(R.id.btn_edit)

      tvNome.text = personagem.nome
      tvClasseRaca.text = "${personagem.classe} - ${personagem.raca}"

      btnDelete.setOnClickListener {
        excluirPersonagem(personagem)
      }

      btnEdit.setOnClickListener {
        editarPersonagem(personagem)
      }

      return view
    }
  }

  private fun excluirPersonagem(personagem: PersonagemEntity) {
    lifecycleScope.launch {
      try {
        withContext(Dispatchers.IO) {
          personagemDao.delete(personagem)
        }
        carregarListaPersonagens() // Recarregar a lista após exclusão
        Toast.makeText(this@ListCharactersActivity, "Personagem excluído.", Toast.LENGTH_SHORT).show()
      } catch (e: Exception) {
        Log.e("MeuApp", "Erro ao excluir personagem: ${e.message}", e)
        Toast.makeText(this@ListCharactersActivity, "Não foi possível excluir o personagem.", Toast.LENGTH_SHORT).show()
      }
    }
  }

  private fun editarPersonagem(personagem: PersonagemEntity) {
    // Aqui você pode iniciar uma nova activity e passar os dados do personagem
    val intent = Intent(this, EditCharacterActivity::class.java)
    intent.putExtra("personagem_id", personagem.id) // Passar o ID do personagem
    startActivity(intent)
  }
}
