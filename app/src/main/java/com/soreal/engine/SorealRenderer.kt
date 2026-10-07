package com.soreal.engine
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
class SorealRenderer:GLSurfaceView.Renderer{private val n=NativeRenderer();override fun onSurfaceCreated(g:GL10?,c:EGLConfig?)=n.onSurfaceCreated();override fun onSurfaceChanged(g:GL10?,w:Int,h:Int)=n.onSurfaceChanged(w,h);override fun onDrawFrame(g:GL10?)=n.onDrawFrame()}
private class NativeRenderer{private external fun nativeInit();private external fun nativeResize(width:Int,height:Int);private external fun nativeFrame();fun onSurfaceCreated()=nativeInit();fun onSurfaceChanged(w:Int,h:Int)=nativeResize(w,h);fun onDrawFrame()=nativeFrame();companion object{init{System.loadLibrary("soreal")}}}
