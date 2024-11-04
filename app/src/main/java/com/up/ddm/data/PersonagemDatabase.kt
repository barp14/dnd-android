package com.up.ddm.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [PersonagemEntity::class], version = 1, exportSchema = false)
abstract class PersonagemDatabase : RoomDatabase() {
    abstract fun personagemDao(): PersonagemDao
}
