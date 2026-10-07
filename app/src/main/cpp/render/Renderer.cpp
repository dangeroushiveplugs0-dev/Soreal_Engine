#include "Renderer.h"
#include <GLES3/gl3.h>
#include <android/log.h>
namespace soreal{void Renderer::initialize(){__android_log_print(ANDROID_LOG_INFO,"SorealRenderer","GLES 3 smoke renderer initialized");glDisable(GL_DITHER);}void Renderer::resize(int w,int h){width_=w>0?w:1;height_=h>0?h:1;glViewport(0,0,width_,height_);}void Renderer::frame(){glClearColor(.035f,.04f,.055f,1.f);glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);}}
