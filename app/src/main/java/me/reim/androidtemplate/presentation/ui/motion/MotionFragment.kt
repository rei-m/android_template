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

package me.reim.androidtemplate.presentation.ui.motion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import me.reim.androidtemplate.R

class MotionFragment : Fragment() {

    companion object {
        fun newInstance() = MotionFragment()
    }

    private lateinit var viewModel: MotionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.motion_root_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MotionViewModel::class.java)
        // TODO: Use the ViewModel
        view?.findViewById<Button>(R.id.motion_scene_1)?.setOnClickListener {
            requireFragmentManager()
                .beginTransaction()
                .add(R.id.container, SceneFragment.newInstance(R.layout.motion_scene_1_fragment))
                .commit()
        }
        view?.findViewById<Button>(R.id.motion_scene_2)?.setOnClickListener {
            requireFragmentManager()
                .beginTransaction()
                .add(R.id.container, SceneFragment.newInstance(R.layout.motion_scene_2_fragment))
                .commit()
        }
        view?.findViewById<Button>(R.id.motion_scene_3)?.setOnClickListener {
            requireFragmentManager()
                .beginTransaction()
                .add(R.id.container, SceneFragment.newInstance(R.layout.motion_scene_3_fragment))
                .commit()
        }
        view?.findViewById<Button>(R.id.motion_scene_6)?.setOnClickListener {
            requireFragmentManager()
                .beginTransaction()
                .add(R.id.container, SceneFragment.newInstance(R.layout.motion_scene_6_fragment))
                .commit()
        }
        view?.findViewById<Button>(R.id.motion_scene_7)?.setOnClickListener {
            requireFragmentManager()
                .beginTransaction()
                .add(R.id.container, SceneFragment.newInstance(R.layout.motion_scene_7_fragment))
                .commit()
        }
        view?.findViewById<Button>(R.id.motion_scene_9)?.setOnClickListener {
            requireFragmentManager()
                .beginTransaction()
                .add(R.id.container, SceneFragment.newInstance(R.layout.motion_scene_9_fragment))
                .commit()
        }
    }
}
