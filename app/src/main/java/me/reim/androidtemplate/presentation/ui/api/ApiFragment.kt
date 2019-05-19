/*
 * Copyright (c) 2018. Rei Matsushita
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package me.reim.androidtemplate.presentation.ui.api

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerFragment
import me.reim.androidtemplate.databinding.ApiFragmentBinding
import me.reim.androidtemplate.feature.common.di.ForFragment
import javax.inject.Inject

class ApiFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ApiViewModel.Factory

    private lateinit var binding: ApiFragmentBinding

    private lateinit var viewModel: ApiViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ApiFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        obtainViewModel()
        with(binding) {
            apiRecycler.adapter = ArticlesAdapter()
            apiRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    println("onScrolled")
                    println("${dy}dy")
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    println("onScrollStateChanged")
                    println("${newState}newState")
                }
            })
            binding.setLifecycleOwner(this@ApiFragment)
            viewModel = this@ApiFragment.viewModel
        }
    }

    private fun obtainViewModel() {
        viewModelFactory.userId = "rei-m"
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(ApiViewModel::class.java)
    }

    @dagger.Module
    abstract class Module {
        @ForFragment
        @ContributesAndroidInjector
        abstract fun contributeInjector(): ApiFragment
    }

    companion object {
        fun newInstance() = ApiFragment()
    }
}
