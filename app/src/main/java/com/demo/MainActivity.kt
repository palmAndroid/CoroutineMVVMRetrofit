package com.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.adapter.MainListAdapter
import com.demo.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var countriesAdapter  = MainListAdapter(arrayListOf())
    lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.refresh()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
        textView.visibility = View.GONE
        progressBar.visibility =View.VISIBLE
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                progressBar.visibility = View.GONE
                textView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                countriesAdapter.updateCountries(countries)
            }
        })
        viewModel.countryLoadError.observe(this, Observer { countries ->
            countries?.let {
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.GONE
                textView.visibility = View.VISIBLE
                textView.text = it
            }
        })
    }
}