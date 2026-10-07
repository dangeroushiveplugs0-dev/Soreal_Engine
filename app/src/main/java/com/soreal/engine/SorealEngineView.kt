package com.soreal.engine
import android.content.Context
import android.opengl.GLSurfaceView
import android.view.MotionEvent
class SorealEngineView(context:Context):GLSurfaceView(context){
    private var lastX=0f
    private var lastY=0f
    init{setEGLContextClientVersion(3);setRenderer(SorealRenderer());renderMode=RENDERMODE_CONTINUOUSLY;keepScreenOn=true}
    override fun onTouchEvent(e:MotionEvent):Boolean{
        when(e.actionMasked){
            MotionEvent.ACTION_DOWN->{lastX=e.x;lastY=e.y;return true}
            MotionEvent.ACTION_MOVE->{val dx=e.x-lastX;val dy=e.y-lastY;lastX=e.x;lastY=e.y;queueEvent{NativeRenderer.touch(dx,dy)};return true}
        }
        return true
    }
}