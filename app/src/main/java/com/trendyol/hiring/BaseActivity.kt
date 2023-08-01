package com.trendyol.hiring

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class BaseActivity : AppCompatActivity() {

    abstract val fragmentContainerId: Int

    abstract val fragmentManager: FragmentManager

    /**
     * You can access currently visible fragment by using the property
      */
    var currentFragment: Fragment? = null

    fun addFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        currentFragment = fragment
        fragmentManager.beginTransaction()
            .add(fragmentContainerId, fragment, fragment::class.java.name)
            .apply { if (addToBackStack) addToBackStack(fragment::class.java.name) }
            .commit()
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        currentFragment = fragment
        fragmentManager.beginTransaction()
            .replace(fragmentContainerId, fragment, fragment::class.java.name)
            .apply { if (addToBackStack) addToBackStack(fragment::class.java.name) }
            .commit()
    }
}
