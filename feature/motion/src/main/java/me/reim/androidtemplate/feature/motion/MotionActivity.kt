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

package me.reim.androidtemplate.feature.motion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MotionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.motion_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, MotionFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.size > 1) {
            supportFragmentManager
                .beginTransaction()
                .remove(supportFragmentManager.fragments.last())
                .commit()
            return
        }
        super.onBackPressed()
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, MotionActivity::class.java)
    }
}
