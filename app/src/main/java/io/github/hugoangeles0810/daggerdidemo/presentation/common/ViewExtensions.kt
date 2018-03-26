package io.github.hugoangeles0810.daggerdidemo.presentation.common

import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatImageView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import io.github.hugoangeles0810.daggerdidemo.App

fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(layout, this, attachToRoot)
}

fun AppCompatImageView.load(url: String?) {
    Glide.with(this)
            .load(url)
            .into(this)
}

val AppCompatActivity.component
    get() = (application as App).component