#include "VulkanCapabilities.h"
#include <vulkan/vulkan.h>
#include <android/log.h>
namespace soreal{VulkanCapabilities queryVulkanCapabilities(){VulkanCapabilities r{};uint32_t v=VK_API_VERSION_1_0;if(vkEnumerateInstanceVersion(&v)!=VK_SUCCESS)return r;r.supported=true;r.apiMajor=VK_VERSION_MAJOR(v);r.apiMinor=VK_VERSION_MINOR(v);__android_log_print(ANDROID_LOG_INFO,"SorealVulkan","Vulkan detected: %d.%d",r.apiMajor,r.apiMinor);return r;}}
