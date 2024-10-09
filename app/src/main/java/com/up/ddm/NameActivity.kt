package com.up.ddm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NameActivity : AppCompatActivity() {
    private lateinit var editTextNome: EditText
    private lateinit var buttonProximo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        editTextNome = findViewById(R.id.editTextNome)
        buttonProximo = findViewById(R.id.buttonProximo)

        buttonProximo.setOnClickListener {
            val nome = editTextNome.text.toString()
            val intent = Intent(this, RacaClasseActivity::class.java)
            intent.putExtra("nome", nome)
            startActivity(intent)
        }
    }
}
