package com.up.ddm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import PersonagemPack.Personagem

class DisplayCharacterActivity : AppCompatActivity() {

    private lateinit var tvNome: TextView
    private lateinit var tvRaca: TextView
    private lateinit var tvClasse: TextView
    private lateinit var tvVida: TextView
    private lateinit var tvForca: TextView
    private lateinit var tvDestreza: TextView
    private lateinit var tvConstituicao: TextView
    private lateinit var tvInteligencia: TextView
    private lateinit var tvSabedoria: TextView
    private lateinit var tvCarisma: TextView
    private lateinit var btnReiniciar: Button

    private lateinit var personagem: Personagem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_character)

        tvNome = findViewById(R.id.tv_nome)
        tvRaca = findViewById(R.id.tv_raca)
        tvClasse = findViewById(R.id.tv_classe)
        tvVida = findViewById(R.id.tv_vida)
        tvForca = findViewById(R.id.tv_forca)
        tvDestreza = findViewById(R.id.tv_destreza)
        tvConstituicao = findViewById(R.id.tv_constituicao)
        tvInteligencia = findViewById(R.id.tv_inteligencia)
        tvSabedoria = findViewById(R.id.tv_sabedoria)
        tvCarisma = findViewById(R.id.tv_carisma)
        btnReiniciar = findViewById(R.id.btn_reiniciar)

        // Recuperar o personagem passado
        personagem = intent.getSerializableExtra("personagem") as Personagem

        // Exibir informações
        tvNome.text = "Nome: ${personagem.nome}"
        tvRaca.text = "Raça: ${personagem.raca.nomeRaca}"
        tvClasse.text = "Classe: ${personagem.classe.nomeClasse}"
        tvVida.text = "Vida: ${personagem.vida}"
        tvForca.text = "Força: ${personagem.forca}"
        tvDestreza.text = "Destreza: ${personagem.destreza}"
        tvConstituicao.text = "Constituição: ${personagem.constituicao}"
        tvInteligencia.text = "Inteligência: ${personagem.inteligencia}"
        tvSabedoria.text = "Sabedoria: ${personagem.sabedoria}"
        tvCarisma.text = "Carisma: ${personagem.carisma}"

        btnReiniciar.setOnClickListener {
            // Reiniciar o processo de criação de personagem
            val intent = Intent(this, CreateCharacterActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}