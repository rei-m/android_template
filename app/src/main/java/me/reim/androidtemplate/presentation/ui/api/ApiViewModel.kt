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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.reim.androidtemplate.data.model.Article
import me.reim.androidtemplate.data.repository.ArticleRepository
import javax.inject.Inject

class ApiViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _articles: MutableLiveData<List<Article>> = MutableLiveData()
    val articles: LiveData<List<Article>> = _articles

    private val _refreshing: MutableLiveData<Boolean> = MutableLiveData()
    val refreshing: LiveData<Boolean> = _refreshing

    private val job = Job()

    init {
        _refreshing.value = false
        GlobalScope.launch(job + Dispatchers.Main) {
            val articles = articleRepository.fetchArticles("rei-m")
            _articles.postValue(articles)
        }
    }

    override fun onCleared() {
        _refreshing.value = false
        job.cancel()
        super.onCleared()
    }

    fun onRefresh() {
        GlobalScope.launch(job + Dispatchers.Main) {
            val articles = articleRepository.fetchArticles("rei-m")
            _articles.postValue(articles)
            _refreshing.value = false
        }
    }

    class Factory @Inject constructor(private val articleRepository: ArticleRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ApiViewModel(articleRepository) as T
        }
    }
}
