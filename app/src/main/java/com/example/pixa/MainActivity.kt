package com.example.pixa

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.pixa.databinding.ActivityMainBinding
//import com.example.pixal.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var download = true
    private lateinit var binding: ActivityMainBinding
    var adapter = ImageAdapter()
    private var page = 1
    private lateinit var layoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
        setting()
        imageListener()

    }

    private fun setting() {
        layoutManager = LinearLayoutManager(this)
        binding.recyclerV.adapter = adapter
    }

    private fun initClickers() {
        with(binding) {
            btnFetch.setOnClickListener {
                doRequest()
            }
        }

    }

    private fun imageListener() {
        binding.recyclerV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (!download) {
                    if (lastPosition == adapter.itemCount - 1) {
                        page++
                        doRequest()
                    }
                }
            }
        })
    }


    private fun doRequest() {
        download = true
        binding.progressBar.visibility = View.VISIBLE
        RetrofitService().api.getImage(
            binding.etInput.text.toString(),
            page = page
        ).enqueue(object : Callback<PixaModel> {
            override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                if (response.isSuccessful) {
                    adapter.setImages(response.body()?.hits!!)
                    Log.d("image_data-data", "${response.body()}")
                    download = false
                    binding.progressBar.visibility = View.GONE

                } else {
                    Log.d("issuccess", "false")
                    download = false
                    binding.progressBar.visibility = View.GONE
                }
            }


            override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                download = false
                binding.progressBar.visibility = View.GONE
                Log.e("ololo", "onFailure: ${t.message}")
            }
        }
        )
    }
}