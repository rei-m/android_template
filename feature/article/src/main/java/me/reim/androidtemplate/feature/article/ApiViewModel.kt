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

package me.reim.androidtemplate.feature.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.reim.androidtemplate.model.Article
import me.reim.androidtemplate.model.ArticleRepository
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ApiViewModel(
    private val userId: String,
    private val articleRepository: ArticleRepository
) : ViewModel(), CoroutineScope {

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val _articles: MutableLiveData<List<Article>> = MutableLiveData()
    val articles: LiveData<List<Article>>
        get() {
            _articles.value ?: loadArticles()
            return _articles
        }

    private val _refreshing: MutableLiveData<Boolean> = MutableLiveData()
    val refreshing: LiveData<Boolean> = _refreshing

    init {
        _refreshing.value = false
    }

    override fun onCleared() {
        _refreshing.value = false
        job.cancel()
        super.onCleared()
    }

    fun onRefresh() {
        loadArticles()
    }

    private fun loadArticles() {
        launch {
            val articles = articleRepository.fetchArticles(userId)
            _articles.postValue(articles)
            _refreshing.postValue(false)
        }
    }

    class Factory @Inject constructor(private val articleRepository: ArticleRepository) : ViewModelProvider.Factory {

        var userId: String = ""

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ApiViewModel(userId, articleRepository) as T
        }
    }
}
