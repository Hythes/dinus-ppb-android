package com.hanif.lantam.bridge

import com.hanif.lantam.model.BeritaModel
import com.hanif.lantam.model.BeritaSingleModel
import com.hanif.lantam.model.GetOneBeritaModel
import com.hanif.lantam.model.PostBeritaModel
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("get.php")
    fun getOne(@Query("id") id: String): Call<BeritaSingleModel?>

    @GET("get.php")
    fun getAll(): Call<BeritaModel?>

    @FormUrlEncoded
    @POST("delete.php")
    fun delete(@Field("id") id: String): Call<BeritaSingleModel?>

    @FormUrlEncoded
    @POST("create.php")
    fun post(
        @Field("judul") judul: String,
        @Field("isi") isi: String,
        @Field("foto") foto: String
    ): Call<BeritaSingleModel?>
}