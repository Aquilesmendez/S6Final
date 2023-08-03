package com.example.s6final.modelo.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.s6final.modelo.local.entities.DetailsPhoneEntity
import com.example.s6final.modelo.local.entities.PhoneEntity

@Dao
interface PhoneDao {

    // insertar lista

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhone(listPhone: List<PhoneEntity>)


    @Query("SELECT * FROM TABLE_PHONE  ORDER BY id ASC")
    fun getAllPhone(): LiveData<List<PhoneEntity>>


    // INSERTA UN ELEMENTO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoneDetail(phone: DetailsPhoneEntity)


    // da error por momentanemante no se ocupa
    @Query("SELECT * FROM TABLE_PHONE_DETAILS  WHERE id=:id")
    fun  getPhoneDetailByID(id:String): LiveData<DetailsPhoneEntity?>
}