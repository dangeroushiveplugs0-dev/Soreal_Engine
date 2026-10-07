#include "VulkanCapabilities.h"
#include <vulkan/vulkan.h>
#include <android/log.h>

namespace soreal {

VulkanCapabilities queryVulkanCapabilities() {
    VulkanCapabilities result{};

    // Android's system Vulkan loader does not guarantee that
    // vkEnumerateInstanceVersion is exported as a link-time symbol.
    // Resolve it through the loader instead, falling back to Vulkan 1.0.
    uint32_t version = VK_API_VERSION_1_0;
    auto getInstanceProcAddr = vkGetInstanceProcAddr;
    if (!getInstanceProcAddr) {
        return result;
    }

    using EnumerateInstanceVersionFn = VkResult (*)(uint32_t*);
    auto enumerateInstanceVersion =
        reinterpret_cast<EnumerateInstanceVersionFn>(
            getInstanceProcAddr(nullptr, "vkEnumerateInstanceVersion"));

    if (enumerateInstanceVersion) {
        if (enumerateInstanceVersion(&version) != VK_SUCCESS) {
            version = VK_API_VERSION_1_0;
        }
    }

    result.supported = true;
    result.apiMajor = VK_VERSION_MAJOR(version);
    result.apiMinor = VK_VERSION_MINOR(version);

    __android_log_print(
        ANDROID_LOG_INFO,
        "SorealVulkan",
        "Vulkan detected: %d.%d",
        result.apiMajor,
        result.apiMinor);

    return result;
}

} // namespace soreal
