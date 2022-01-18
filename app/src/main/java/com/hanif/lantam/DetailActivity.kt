package com.hanif.lantam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.hanif.lantam.bridge.RetrofitConfig
import com.hanif.lantam.databinding.ActivityDetailBinding
import com.hanif.lantam.model.BeritaModel
import com.hanif.lantam.model.BeritaSingleModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding
    lateinit var idBerita : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        binding.btnHapus.setOnClickListener { btnClickDelete() }
    }
    fun init(){
        idBerita = intent.getStringExtra("id").toString()
        RetrofitConfig.apiService.getOne(idBerita).enqueue(object : Callback<BeritaSingleModel?>{
            override fun onResponse(
                call: Call<BeritaSingleModel?>,
                response: Response<BeritaSingleModel?>
            ) {
                if(response.isSuccessful){
                    placeData(response)
                }
            }

            override fun onFailure(call: Call<BeritaSingleModel?>, t: Throwable) {
                Log.d("R3SPONSE","error")
            }

        })

    }
    fun btnClickDelete(){
        RetrofitConfig.apiService.delete(idBerita).enqueue(object : Callback<BeritaSingleModel?>{
            override fun onResponse(
                call: Call<BeritaSingleModel?>,
                response: Response<BeritaSingleModel?>
            ) {
                if(response.isSuccessful){
                    val res = response.body()
                    Toast.makeText(this@DetailActivity,res?.message,Toast.LENGTH_SHORT)
                    finish()
                }

            }

            override fun onFailure(call: Call<BeritaSingleModel?>, t: Throwable) {
                Log.d("R3SPONSE", "Error")
            }

        })
    }

    fun placeData(response: Response<BeritaSingleModel?>){
        val res = response.body()
        binding.judul.text = res?.data?.judul
        binding.berita.text = res?.data?.isi
        Picasso.get().load(res?.data?.foto).into(binding.fotoFoto)
    }
}