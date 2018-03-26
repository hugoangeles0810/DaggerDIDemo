package io.github.hugoangeles0810.daggerdidemo.presentation.common

import android.support.annotation.StringRes

interface BaseView {

    fun isFinishing(): Boolean
    fun showError(message: String)
    fun showError(@StringRes message: Int)
}