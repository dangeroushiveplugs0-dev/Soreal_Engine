#include "Renderer.h"
#include <GLES3/gl3.h>
#include <android/log.h>
#include <cmath>
#include <vector>
namespace soreal {
static const char* VS = "#version 300 es\nlayout(location=0) in vec3 p; uniform mat4 uMvp; void main(){gl_Position=uMvp*vec4(p,1.0);}";
static const char* FS = "#version 300 es\nprecision mediump float; out vec4 c; void main(){c=vec4(0.34,0.38,0.45,1.0);}";
static unsigned int compile(unsigned int type,const char* src){unsigned int s=glCreateShader(type);glShaderSource(s,1,&src,nullptr);glCompileShader(s);return s;}
static unsigned int makeProgram(){unsigned int v=compile(GL_VERTEX_SHADER,VS),f=compile(GL_FRAGMENT_SHADER,FS),p=glCreateProgram();glAttachShader(p,v);glAttachShader(p,f);glLinkProgram(p);glDeleteShader(v);glDeleteShader(f);return p;}
static void perspective(float* m,float fovy,float aspect,float n,float f){for(int i=0;i<16;i++)m[i]=0;float t=1.0f/tanf(fovy*0.5f);m[0]=t/aspect;m[5]=t;m[10]=(f+n)/(n-f);m[11]=-1;m[14]=(2*f*n)/(n-f);}
static void mul(const float*a,const float*b,float*r){float x[16];for(int c=0;c<4;c++)for(int row=0;row<4;row++){x[c*4+row]=0;for(int k=0;k<4;k++)x[c*4+row]+=a[k*4+row]*b[c*4+k];}for(int i=0;i<16;i++)r[i]=x[i];}
static void identity(float*m){for(int i=0;i<16;i++)m[i]=(i%5)==0?1:0;}
static void translate(float*m,float x,float y,float z){identity(m);m[12]=x;m[13]=y;m[14]=z;}
static void rotateX(float*m,float a){identity(m);float c=cosf(a),s=sinf(a);m[5]=c;m[6]=s;m[9]=-s;m[10]=c;}
static void rotateY(float*m,float a){identity(m);float c=cosf(a),s=sinf(a);m[0]=c;m[2]=-s;m[8]=s;m[10]=c;}
void Renderer::initialize(){glDisable(GL_DITHER);glEnable(GL_DEPTH_TEST);program_=makeProgram();uMvp_=glGetUniformLocation(program_,"uMvp");__android_log_print(ANDROID_LOG_INFO,"SorealRenderer","Grid renderer initialized");}
void Renderer::resize(int w,int h){width_=w>0?w:1;height_=h>0?h:1;glViewport(0,0,width_,height_);}
void Renderer::touch(float dx,float dy){yaw_+=dx*0.008f;pitch_+=dy*0.006f;if(pitch_>1.35f)pitch_=1.35f;if(pitch_<-1.35f)pitch_=-1.35f;}
void Renderer::frame(){glClearColor(.035f,.04f,.055f,1.f);glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);if(!program_)return;
std::vector<float> v; const int n=20; const float step=0.5f; const float extent=n*step;
for(int i=-n;i<=n;i++){float a=i*step;v.insert(v.end(),{a,0,-extent,a,0,extent,-extent,0,a,extent,0,a});}
glUseProgram(program_);float p[16],rx[16],ry[16],t[16],rxy[16],view[16],mvp[16];perspective(p,1.05f,(float)width_/height_,0.1f,100.0f);rotateX(rx,pitch_);rotateY(ry,yaw_);mul(ry,rx,rxy);translate(t,0,-2.2f,-8.5f);mul(t,rxy,view);mul(p,view,mvp);glUniformMatrix4fv(uMvp_,1,GL_FALSE,mvp);glLineWidth(1.0f);glBindBuffer(GL_ARRAY_BUFFER,0);glEnableVertexAttribArray(0);glVertexAttribPointer(0,3,GL_FLOAT,GL_FALSE,0,v.data());glDrawArrays(GL_LINES,0,(GLsizei)(v.size()/3));glDisableVertexAttribArray(0);}
}