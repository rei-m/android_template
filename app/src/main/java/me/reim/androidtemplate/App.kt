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

package me.reim.androidtemplate

import android.content.Context
import androidx.multidex.MultiDex
import com.jakewharton.threetenabp.AndroidThreeTen
import com.squareup.leakcanary.LeakCanary
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import me.reim.androidtemplate.core.data.di.DataModule
import me.reim.androidtemplate.core.infrastructure.di.NetworkModule
import me.reim.androidtemplate.di.ApplicationModule
import me.reim.androidtemplate.feature.article.ApiActivity
import timber.log.Timber
import javax.inject.Singleton

open class App : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        initLeakCanary()
        initTimber()
        initThreeTenABP()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApp_Component.builder()
            .applicationModule(ApplicationModule(this))
//            .networkModule(NetworkModule.instance)
//            .databaseModule(DatabaseModule.instance)
            .build()
    }

    protected open fun initLeakCanary() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this)
        }
    }

    protected open fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
//            Timber.plant(CrashlyticsTree())
        }
    }

    private fun initThreeTenABP() {
        AndroidThreeTen.init(this)

    }

    @Singleton
    @dagger.Component(
        modules = [
            AndroidSupportInjectionModule::class,
            ApplicationModule::class,
            NetworkModule::class,
            DataModule::class,
            ApiActivity.Module::class
        ]
    )
    interface Component : AndroidInjector<App>
}