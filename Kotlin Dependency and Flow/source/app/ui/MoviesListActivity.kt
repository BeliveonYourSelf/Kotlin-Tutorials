package com.learn.app.kotlins.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.app.kotlins.adapters.MovieAdapters
import com.learn.app.kotlins.apputills.ApiState
import com.learn.app.kotlins.apputills.initRecycler
import com.learn.app.kotlins.apputills.isVisiable
import com.learn.app.kotlins.databinding.ActivityMoviesListBinding
import com.learn.app.kotlins.di.viewmodels.MovieStates
import com.learn.app.kotlins.di.viewmodels.MovieViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MoviesListActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMoviesListBinding

    @Inject
    lateinit var movieAdapters: MovieAdapters

    private val movieViewModel: MovieViewModels by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        getData()

    }

    private fun getData() {
        lifecycleScope.launch {
            movieViewModel.movieList.collect { states ->
                if (states.isLoading) {
                    Log.e("TAG", "getData: isLoading", )
                        binding.loading.isVisiable(true,binding.recycler)
                } else if (states.data != null) {
                    Log.e("TAG", "MovieList Data >>>> : states.data != null size --> ${states.data.size}", )
                    binding.loading.isVisiable(false,binding.recycler)
                    binding.recycler.initRecycler(LinearLayoutManager(this@MoviesListActivity),movieAdapters)
                    movieAdapters.differ.submitList(states.data)
                } else {
                    Log.e("TAG", "Failed", )
                    binding.loading.isVisiable(true,binding.recycler)
                }
            }

        }
    }

}