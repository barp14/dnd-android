package com.up.ddm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personagem")
data class PersonagemEntity(
    @PrimaryKey val id: Long,
    val nome: String,
    val forca: Int,
    val destreza: Int,
    val constituicao: Int,
    val inteligencia: Int,
    val sabedoria: Int,
    val carisma: Int,
    val classe: String,
    val raca: String
)