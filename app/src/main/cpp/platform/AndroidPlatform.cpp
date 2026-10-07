#include <jni.h>
#include "../engine/SorealEngine.h"
namespace{soreal::SorealEngine gEngine;}
extern "C" JNIEXPORT void JNICALL Java_com_soreal_engine_NativeRenderer_nativeInit(JNIEnv*,jobject){gEngine.initialize();}
extern "C" JNIEXPORT void JNICALL Java_com_soreal_engine_NativeRenderer_nativeResize(JNIEnv*,jobject,jint w,jint h){gEngine.resize(w,h);}
extern "C" JNIEXPORT void JNICALL Java_com_soreal_engine_NativeRenderer_nativeFrame(JNIEnv*,jobject){gEngine.frame();}
