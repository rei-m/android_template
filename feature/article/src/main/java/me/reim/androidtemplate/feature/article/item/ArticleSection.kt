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

package me.reim.androidtemplate.feature.article.item

import com.xwray.groupie.Item
import com.xwray.groupie.Section
import me.reim.androidtemplate.model.Article

class ArticleSection : Section() {
    fun updateArticles(articles: List<Article>) {
        val header = ArticleHeadItem()
        val list = mutableListOf<Item<*>>(header)
        list.addAll(articles.map(::ArticleItem))
        update(list)
    }
}
