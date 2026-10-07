package com.soreal.engine

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout

class MainActivity : Activity() {
    private lateinit var engineView: SorealEngineView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        engineView = SorealEngineView(this)
        val root = FrameLayout(this)
        root.addView(engineView, FrameLayout.LayoutParams(-1, -1))
        root.addView(SorealEditorOverlay(this), FrameLayout.LayoutParams(-1, -1))
        setContentView(root)
    }

    override fun onPause() { engineView.onPause(); super.onPause() }
    override fun onResume() { super.onResume(); engineView.onResume() }
}
