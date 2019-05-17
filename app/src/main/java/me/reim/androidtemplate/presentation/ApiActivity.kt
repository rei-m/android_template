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

package me.reim.androidtemplate.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.Binds
import dagger.android.AndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import me.reim.androidtemplate.R
import me.reim.androidtemplate.presentation.di.ActivityModule
import me.reim.androidtemplate.presentation.di.ForActivity
import me.reim.androidtemplate.presentation.ui.api.ApiFragment

class ApiActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.api_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ApiFragment.newInstance())
                .commitNow()
        }
    }

    @ForActivity
    @dagger.Subcomponent(
        modules = [
            ActivityModule::class,
            ApiFragment.Module::class
        ]
    )
    interface Subcomponent : AndroidInjector<ApiActivity> {
        @dagger.Subcomponent.Builder
        abstract class Builder : AndroidInjector.Factory<ApiActivity> {
            override fun create(instance: ApiActivity): AndroidInjector<ApiActivity> =
                activityModule(ActivityModule(instance)).build()

            abstract fun activityModule(module: ActivityModule): Builder

            abstract fun build(): AndroidInjector<ApiActivity>
        }
    }

    @dagger.Module(subcomponents = [Subcomponent::class])
    abstract class Module {
        @Binds
        @IntoMap
        @ClassKey(ApiActivity::class)
        internal abstract fun bind(factory: Subcomponent.Builder): AndroidInjector.Factory<*>
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, ApiActivity::class.java)
    }
}
