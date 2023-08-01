package com.trendyol.hiring

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            addFragment(MainFragment())
        }
    }

    override val fragmentContainerId: Int
        get() = R.id.containerMain

    override val fragmentManager: FragmentManager
        get() = supportFragmentManager
}
