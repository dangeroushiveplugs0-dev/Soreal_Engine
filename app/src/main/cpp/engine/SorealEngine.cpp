#include "SorealEngine.h"
#include "../render/Renderer.h"
#include <android/log.h>
namespace{soreal::Renderer gRenderer;}
namespace soreal{void SorealEngine::initialize(){__android_log_print(ANDROID_LOG_INFO,"SorealEngine","Soreal Engine 0.1.0 initialized");gRenderer.initialize();}void SorealEngine::resize(int w,int h){gRenderer.resize(w,h);}void SorealEngine::frame(){gRenderer.frame();}void SorealEngine::touch(float dx,float dy){gRenderer.touch(dx,dy);}}
