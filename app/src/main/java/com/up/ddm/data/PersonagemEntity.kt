package com.up.ddm.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personagem")
data class PersonagemEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  var nome: String,
  val forca: Int,
  val destreza: Int,
  val constituicao: Int,
  val inteligencia: Int,
  val sabedoria: Int,
  val carisma: Int,
  val classe: String,
  val raca: String,
  val vida: Int
)