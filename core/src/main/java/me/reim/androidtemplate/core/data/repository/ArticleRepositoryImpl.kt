/*
 * Copyright (c) 2019. Rei Matsushita
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

package me.reim.androidtemplate.core.data.repository

import me.reim.androidtemplate.core.infrastructure.network.QiitaApi
import me.reim.androidtemplate.model.Article
import me.reim.androidtemplate.model.ArticleRepository
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(private val api: QiitaApi) : ArticleRepository {
    override suspend fun fetchArticles(userName: String): List<Article> = api.getItems("user:$userName").await()
}
