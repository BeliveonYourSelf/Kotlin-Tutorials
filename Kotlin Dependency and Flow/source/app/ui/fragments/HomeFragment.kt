package com.learn.app.kotlins.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.learn.app.kotlins.R
import com.learn.app.kotlins.adapters.CryptoAdapters
import com.learn.app.kotlins.apputills.ApiStatus
import com.learn.app.kotlins.apputills.initRecycler
import com.learn.app.kotlins.apputills.isVisiable
import com.learn.app.kotlins.databinding.FragmentHomeBinding
import com.learn.app.kotlins.di.repository.ApiRepository
import com.learn.app.kotlins.di.viewmodels.CoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    @Inject
    lateinit var cryptoAdapters: CryptoAdapters
    private val coinViewModel: CoinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerview()
        lifecycleScope.launch {
            with(binding) {
                coinViewModel.getCoinList("usd")
                coinViewModel.coinList.observe(viewLifecycleOwner, Observer {
                    when(it.status)
                    {
                        ApiStatus.Status.LOADING ->{
                            Log.e("TAG", "LOADING --> " )
                            mBarLoading.isVisiable(true,recycler)
                        }
                        ApiStatus.Status.SUCCESS ->{
                            Log.e("TAG", "SUCCESS --> ${it.data?.size}", )
                            mBarLoading.isVisiable(false,recycler)
                            cryptoAdapters.differ.submitList(it.data)
                            cryptoAdapters.setClickListener {
                                Log.e("TAG", "Call Back: ------>", )
//                                val direction = Ho
                                findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)

                            }

                        }
                        ApiStatus.Status.ERROR ->{
                            Log.e("TAG", "ERROR --> " )
                            mBarLoading.isVisiable(false,recycler)
                            Toast.makeText(requireContext(), "Something Went App", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }
    }

    private fun setUpRecyclerview() {
        binding.recycler.initRecycler(LinearLayoutManager(requireContext()),cryptoAdapters)
    }

}


