package com.learn.app.kotlins.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.app.kotlins.adapters.ContactsAdapters
import com.learn.app.kotlins.apputills.DataStatus
import com.learn.app.kotlins.apputills.isVisiable
import com.learn.app.kotlins.databinding.ActivityMainBinding
import com.learn.app.kotlins.di.viewmodels.DatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var contacAdapter: ContactsAdapters
    lateinit var binding: ActivityMainBinding
    private val viewModel: DatabaseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            setSupportActionBar(mainToolbar)
            btnShowDialog.setOnClickListener {
                AddContanctFragment().show(supportFragmentManager, AddContanctFragment().tag)
            }
            viewModel.getAllContacts()
            viewModel.contactList.observe(this@MainActivity) { it ->
                when (it.status) {
                    DataStatus.Status.LOADING -> {
                        Toast.makeText(this@MainActivity, "LOADING", Toast.LENGTH_SHORT).show()
                        loading.isVisiable(true, listBody)

                    }

                    DataStatus.Status.SUCCESS -> {
                        it.isEmpty?.let {
                            showEmpty(it)
                        }

                        loading.isVisiable(false, listBody)
                        contacAdapter.differ.submitList(it.data)
                        rvContacts.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = contacAdapter
                        }
                    }

                    DataStatus.Status.ERROR -> {
                        emptyBody.isVisiable(false, listBody)
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            tvNextScreen.setOnClickListener {
                startActivity(Intent(this@MainActivity,MoviesListActivity::class.java))
            }
        }
    }

    private fun showEmpty(isShown : Boolean)
    {
        with(binding) {
            if(isShown)
            {
                emptyBody.isVisiable(true,listBody)
            }else{
                emptyBody.isVisiable(false,listBody)
            }
        }
    }


}