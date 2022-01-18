package com.hanif.lantam.model

import com.google.gson.annotations.SerializedName


data class BeritaModel(

    @SerializedName("status") var status: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: ArrayList<BeritaDataModel>? = arrayListOf()

)
data class BeritaSingleModel(

    @SerializedName("status") var status: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") var data: BeritaDataModel? = null

)

data class BeritaDataModel(
    @SerializedName("id") var id: String? = null,
    @SerializedName("judul") var judul: String? = null,
    @SerializedName("isi") var isi: String? = null,
    @SerializedName("foto") var foto: String? = null,
    @SerializedName("dibuat") var dibuat: String? = null,
    @SerializedName("diedit") var diedit: String? = null
)