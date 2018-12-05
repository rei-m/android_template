package me.reim.androidtemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import me.reim.androidtemplate.databinding.ActivityMainBinding
import me.reim.androidtemplate.presentation.ApiActivity
import me.reim.androidtemplate.presentation.MotionActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
//            R.id.navigation_home -> {
//                message.setText(R.string.title_home)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
//                return@OnNavigationItemSelectedListener true
//            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.buttonApiSample.setOnClickListener {
            startActivity(ApiActivity.createIntent(this))
        }
        binding.buttonMotionSample.setOnClickListener {
            startActivity(MotionActivity.createIntent(this))
        }

//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
