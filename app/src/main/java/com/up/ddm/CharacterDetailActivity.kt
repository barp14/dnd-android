package com.up.ddm

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var attributesTextView: TextView
    private lateinit var confirmButton: Button
    private lateinit var characterName: String
    private lateinit var characterAttributes: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        // Receber o nome e os atributos do personagem da intent
        characterName = intent.getStringExtra("character_name") ?: "Personagem"
        characterAttributes = intent.getStringExtra("character_attributes") ?: "Atributos não definidos"

        // Referências aos TextViews e Botão
        nameTextView = findViewById(R.id.textViewName)
        attributesTextView = findViewById(R.id.textViewAttributes)
        confirmButton = findViewById(R.id.buttonConfirm)

        // Exibir os detalhes do personagem
        nameTextView.text = "Nome: $characterName"
        attributesTextView.text = "Atributos: $characterAttributes"

        confirmButton.setOnClickListener {
            // Criar um objeto Character
            val character = Character(characterName, characterAttributes)

            // Aqui você pode implementar a lógica para salvar o personagem
            saveCharacter(character)

            // Exibir mensagem de confirmação
            Toast.makeText(this, "Personagem ${character.name} criado!", Toast.LENGTH_SHORT).show()

            // Retornar para a tela inicial ou outra atividade
            finish() // Finaliza a atividade atual
        }
    }

    private fun saveCharacter(character: Character) {
        // Implementar lógica de salvamento, por exemplo, em um banco de dados ou lista
        // Exemplo simples: armazenar em uma lista (substitua por um banco de dados conforme necessário)
        CharacterRepository.addCharacter(character)
    }
}
