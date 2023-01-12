package com.keuapps.baseapp.data.remote

import com.keuapps.baseapp.data.remote.model.CRUDResponse
import com.keuapps.baseapp.data.remote.model.NoteResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitAPI {
    @GET("/kisiler/tum_kisiler.php")
    suspend fun getContact (
    ) : Response<NoteResponse>

    @POST("/kisiler/insert_kisiler.php")
    @FormUrlEncoded
    suspend fun addNewContact(
        @Field("kisi_ad") kisi_ad:String,
        @Field("kisi_tel") kisi_tel:String
    ) : Response<CRUDResponse>
}