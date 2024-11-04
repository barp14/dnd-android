package com.up.ddm.data

import androidx.room.*

@Dao
interface PersonagemDao {
    @Query("SELECT * FROM personagem WHERE id = :id")
    suspend fun getPersonagemById(id: Long): PersonagemEntity?

    @Insert
    suspend fun insert(personagem: PersonagemEntity): Long

    @Update
    suspend fun update(personagem: PersonagemEntity): Int

    @Delete
    suspend fun delete(personagem: PersonagemEntity): Int
}