package com.example.s6final.modelo.remote.apiRetrofit

import com.example.s6final.modelo.remote.fromInternet.DetailsPhoneApiClass
import com.example.s6final.modelo.remote.fromInternet.PhoneApiClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PhoneApi {

    // es el que te agrega los links  products y el otro que dice details

    //listado de zapatos
    @GET("products")
    suspend fun fetchPhoneList(): Response<List<PhoneApiClass>>

    //seleccionar un zapato con detalle
    @GET("details/{id}")
    suspend fun fechPhoneDetail(@Path("id") id: String): Response<DetailsPhoneApiClass>
}