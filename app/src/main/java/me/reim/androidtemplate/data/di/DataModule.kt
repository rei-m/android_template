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

package me.reim.androidtemplate.data.di

import dagger.Module
import dagger.Provides
import me.reim.androidtemplate.data.repository.ArticleRepository
import me.reim.androidtemplate.data.repository.ArticleRepositorySource
import me.reim.androidtemplate.infrastructure.network.QiitaApi
import javax.inject.Singleton

@Module
open class DataModule {
    @Singleton
    @Provides
    fun provideArticleRepository(api: QiitaApi): ArticleRepository = ArticleRepositorySource(api)
}
