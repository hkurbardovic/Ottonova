package com.hkurbardovic.ottonova.base

import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.hkurbardovic.ottonova.R
import com.hkurbardovic.ottonova.utilities.inTransaction
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    fun addFragment(fragment: Fragment) {
        if (fragment.isAdded) return
        supportFragmentManager.inTransaction {
            setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )

            addToBackStack(fragment::class.java.name)
            add(R.id.fragment_container, fragment)
        }
    }

    fun replaceFragment(fragment: Fragment) {
        if (fragment.isAdded) return
        supportFragmentManager.inTransaction {
            replace(R.id.fragment_container, fragment)
        }
    }

    fun showMessage(message: String, coordinatorLayout: CoordinatorLayout) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show()
    }
}