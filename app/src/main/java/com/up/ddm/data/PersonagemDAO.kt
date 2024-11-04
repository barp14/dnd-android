package com.up.ddm.data

import androidx.room.*

@Dao
interface PersonagemDao {
    @Query("SELECT * FROM personagem")
    fun getAllPersonagens(): List<PersonagemEntity>

    @Query("SELECT * FROM personagem WHERE id = :id")
    fun getPersonagemById(id: Long): PersonagemEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(personagem: PersonagemEntity): Long

    @Update
    fun update(personagem: PersonagemEntity): Int

    @Delete
    fun delete(personagem: PersonagemEntity): Int
}