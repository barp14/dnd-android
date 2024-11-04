package com.up.ddm.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [PersonagemEntity::class], version = 1, exportSchema = true)
abstract class PersonagemDatabase : RoomDatabase() {
    abstract fun personagemDao(): PersonagemDao

    companion object {
        @Volatile
        private var INSTANCE: PersonagemDatabase? = null

        fun getDatabase(context: Context): PersonagemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonagemDatabase::class.java,
                    "personagem_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}