package com.soreal.engine
import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.Toast
class MainActivity : Activity() {
    private lateinit var engineView:SorealEngineView
    private val importRequest=1001
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        engineView=SorealEngineView(this)
        val root=FrameLayout(this)
        root.addView(engineView,FrameLayout.LayoutParams(-1,-1))
        root.addView(SorealEditorOverlay(this){openImportPicker()},FrameLayout.LayoutParams(-1,-1))
        setContentView(root)
    }
    private fun openImportPicker(){
        val intent=Intent(Intent.ACTION_OPEN_DOCUMENT).apply{addCategory(Intent.CATEGORY_OPENABLE);type="*/*"}
        startActivityForResult(intent,importRequest)
    }
    override fun onActivityResult(requestCode:Int,resultCode:Int,data:Intent?){
        super.onActivityResult(requestCode,resultCode,data)
        if(requestCode==importRequest && resultCode==RESULT_OK){
            val uri:Uri?=data?.data
            if(uri!=null) Toast.makeText(this,"Selected: "+(uri.lastPathSegment ?: "file"),Toast.LENGTH_SHORT).show()
        }
    }
    override fun onPause(){engineView.onPause();super.onPause()}
    override fun onResume(){super.onResume();requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;engineView.onResume()}
}