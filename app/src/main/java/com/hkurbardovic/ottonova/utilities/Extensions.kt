package com.hkurbardovic.ottonova.utilities

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Allows calls like
 *
 * `supportFragmentManager.inTransaction { add(...) }`
 */
inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}
