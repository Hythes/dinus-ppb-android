package com.hanif.lantam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hanif.lantam.adapter.MainAdapter
import com.hanif.lantam.bridge.RetrofitConfig
import com.hanif.lantam.model.BeritaDataModel
import com.hanif.lantam.model.BeritaModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity()  {

    lateinit var btn: Button
    lateinit var recyclerView: RecyclerView
    lateinit var mainAdapter: MainAdapter
    lateinit var  btnRefresh : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.btn_input_data)
        recyclerView = findViewById(R.id.rv_main)
        btnRefresh = findViewById(R.id.btn_refresh)
        callData()
        btn.setOnClickListener { clickBtn() }
        btnRefresh.setOnClickListener { refreshBtn() }


    }
    fun refreshBtn(){
        callData()
    }
    fun clickBtn(){
        val intent = Intent(this,InputActivity::class.java)
        startActivity(intent)
    }

    fun callData() {
        RetrofitConfig.apiService.getAll().enqueue(object : Callback<BeritaModel?> {
            override fun onResponse(call: Call<BeritaModel?>, response: Response<BeritaModel?>) {
                if (response.isSuccessful) {
                    val res = response.body()
                    mainAdapter = MainAdapter(res?.data)
                    recyclerView.adapter = mainAdapter
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    handleClickAdapter()
                }
            }

            override fun onFailure(call: Call<BeritaModel?>, t: Throwable) {
                Log.d("R3SPONSE", "${t.message}")
            }

        })
    }

    fun handleClickAdapter(){
        mainAdapter.onItemClick = { berita ->
            val intent = Intent(this@MainActivity,DetailActivity::class.java)
            intent.putExtra("id",berita?.id)
            startActivityForResult(intent,0)
        }
    }
}