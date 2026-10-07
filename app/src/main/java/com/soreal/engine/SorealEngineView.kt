package com.soreal.engine
import android.content.Context
import android.opengl.GLSurfaceView
class SorealEngineView(context:Context):GLSurfaceView(context){init{setEGLContextClientVersion(3);setRenderer(SorealRenderer());renderMode=RENDERMODE_CONTINUOUSLY;keepScreenOn=true}}
