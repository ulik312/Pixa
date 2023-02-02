package com.example.pixa

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.pixa.databinding.ActivityMainBinding
//import com.example.pixal.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter = ImageAdapter(listOf())
    private var page : Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()
        pagination()
    }

    private fun initClicker() {
        binding.fetchBtn.setOnClickListener {
            onRequest()
            page = 1
        }
    }

    private fun onRequest() {
        with(binding) {
            RetrofitService().api.getImage(keyWord = edWord.text.toString(),page = page).enqueue(
                object : Callback<PixaModel> {
                    override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                        if (response.isSuccessful) {
                            adapter = ImageAdapter(response.body()?.hits!!)
                            recyclerView.adapter = adapter
                        }
                    }
                    override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                        Log.e("ololo", "OnFailure: ${t.message}")
                    }
                }
            )
        }
    }

    private fun pagination(){
        binding.recyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0){
                    onRequest()
                    page++
                }
            }
        })
    }
}