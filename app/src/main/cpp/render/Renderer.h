#pragma once
namespace soreal {
class Renderer {
public:
    void initialize();
    void resize(int width, int height);
    void frame();
    void touch(float dx, float dy);
private:
    int width_=1, height_=1;
    float yaw_=0.0f, pitch_=0.45f;
    unsigned int program_=0;
    int uMvp_=-1;
};
}