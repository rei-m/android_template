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

import com.xwray.groupie.databinding.BindableItem
import me.reim.androidtemplate.feature.article.R
import me.reim.androidtemplate.feature.article.databinding.ItemArticleBinding
import me.reim.androidtemplate.model.Article

class ArticleItem(
    private val article: Article
) : BindableItem<ItemArticleBinding>() {

    override fun getLayout() = R.layout.item_article

    override fun bind(viewBinding: ItemArticleBinding, position: Int) {
        with(viewBinding) {
            article = this@ArticleItem.article
        }
    }
}