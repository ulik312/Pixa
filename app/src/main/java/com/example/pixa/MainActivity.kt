package com.example.pixa

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pixa.databinding.ActivityMainBinding
//import com.example.pixal.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var adapter = ImageAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicker()

    }

    private fun initClicker() {
        binding.fetchBtn.setOnClickListener {
            onRequest()
        }
    }

    private fun onRequest() {

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.e("DY", "" + dy)
                if (dy > 5)
                    RetrofitService().api.getImage(keyWord = binding.edWord.text.toString()).enqueue(
                        object : Callback<PixaModel>{
                            override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                                if (response.isSuccessful){
                                    adapter = ImageAdapter(response.body()?.hits!!)
                                    binding.recyclerView.adapter = adapter
                                }
                            }
                            override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                                Log.e("ololo", "OnFailure: ${t.message}")
                            }

                        }
                    )
            }
        })


    }
}