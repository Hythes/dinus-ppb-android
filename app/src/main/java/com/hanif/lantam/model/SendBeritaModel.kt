package com.hanif.lantam.model

import com.google.gson.annotations.SerializedName

data class GetOneBeritaModel(
    @SerializedName("id") val id : String? = null
)

data class PostBeritaModel(
    @SerializedName("judul") val judul : String? = null,
    @SerializedName("isi") val isi : String? = null,
    @SerializedName("foto") val foto : String? = null
)