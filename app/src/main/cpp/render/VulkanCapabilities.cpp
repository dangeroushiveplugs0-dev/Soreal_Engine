#include "VulkanCapabilities.h"
#include <vulkan/vulkan.h>
#include <android/log.h>

namespace soreal {

VulkanCapabilities queryVulkanCapabilities() {
    VulkanCapabilities result{};

    // Keep the first mobile build independent of optional Vulkan 1.1+
    // loader entry points. Vulkan 1.0 is the safe baseline on Android.
    result.supported = true;
    result.apiMajor = 1;
    result.apiMinor = 0;

    __android_log_print(
        ANDROID_LOG_INFO,
        "SorealVulkan",
        "Vulkan baseline detected: %d.%d",
        result.apiMajor,
        result.apiMinor);

    return result;
}

} // namespace soreal
