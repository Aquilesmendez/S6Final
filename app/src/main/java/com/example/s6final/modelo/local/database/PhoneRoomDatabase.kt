package com.example.s6final.modelo.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.s6final.modelo.local.entities.DetailsPhoneEntity
import com.example.s6final.modelo.local.PhoneDao
import com.example.s6final.modelo.local.entities.PhoneEntity

@Database(entities = [PhoneEntity::class, DetailsPhoneEntity::class], version = 2,
    exportSchema = false)

abstract class PhoneRoomDatabase : RoomDatabase() {

    // Representacion del dao
    abstract fun getPhoneDao() : PhoneDao  // esto luego se va a usar en el VIEW MODEL

    companion object {
        @Volatile
        private var INSTANCE: PhoneRoomDatabase? = null

        fun getDataBase(context: Context): PhoneRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneRoomDatabase::class.java, "phone_database"
                )
                    .fallbackToDestructiveMigration() // Agregar esta línea
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}