package com.hanif.lantam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.hanif.lantam.bridge.RetrofitConfig
import com.hanif.lantam.databinding.ActivityInputBinding
import com.hanif.lantam.model.BeritaModel
import com.hanif.lantam.model.BeritaSingleModel
import com.hanif.lantam.model.PostBeritaModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {
    lateinit var activityInputBinding: ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(activityInputBinding.root)
        activityInputBinding.btnKirim.setOnClickListener { btnClick() }
    }

    fun btnClick() {
        try {
            val rb = activityInputBinding
            RetrofitConfig.apiService.post(
                "${rb.judul.text}",
                "${rb.isi.text}",
                "${rb.foto.text}",
            ).enqueue(object : Callback<BeritaSingleModel?> {
                override fun onResponse(
                    call: Call<BeritaSingleModel?>,
                    response: Response<BeritaSingleModel?>
                ) {
                    if (response.isSuccessful) {
                        val toast = Toast.makeText(
                            this@InputActivity,
                            "Berhasil membuat data",
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                        finish()
                    }

                }

                override fun onFailure(call: Call<BeritaSingleModel?>, t: Throwable) {
                    Log.d("InputActivity", "${t.message}")
                }

            })
        }catch (t : Throwable){
            Log.d("InputActivity", "${t.message}")
        }
    }
}