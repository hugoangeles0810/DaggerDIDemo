package io.github.hugoangeles0810.daggerdidemo.presentation.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.hugoangeles0810.daggerdidemo.presentation.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(MainActivity.newInstance(this@SplashActivity))
        finish()
    }
}