package com.example.s6final.modelo

import com.example.s6final.modelo.remote.fromInternet.DetailsPhoneApiClass
import com.example.s6final.modelo.local.entities.DetailsPhoneEntity
import com.example.s6final.modelo.remote.fromInternet.PhoneApiClass
import com.example.s6final.modelo.local.entities.PhoneEntity

//el mapper traer la información de la API y lo guarda en la database del telefono

fun fromInternetToPhoneEntity(phoneList: List<PhoneApiClass>): List<PhoneEntity>{ // se usa luego en el Repository
// it representa al phoneApi
    return phoneList.map {

        PhoneEntity(
            id= it.id,
            name = it.name,
            price = it.price,
            image = it.image,

        )
    }
}


fun fromInternetToPhoneDetailEntity(shoes: DetailsPhoneApiClass): DetailsPhoneEntity { // se usa luego en el Repository

    return DetailsPhoneEntity(
        id=shoes.id,
        name = shoes.name,
        price = shoes.price,
        image = shoes.image,
        description = shoes.description,
        lastPrice = shoes.lastPrice,
        credit = shoes.credit,


    )

}