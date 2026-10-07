# Soreal Engine

Mobile-first 3D animation and scene engine.

Foundation 0.1: native C++ Android runtime, GLES smoke renderer, Vulkan capability seam, and GitHub Actions APK builds.

Performance is a first-class requirement: Vulkan-first rendering, mobile forward rendering, minimal allocations, batching, instancing, culling, LOD/streaming and scalable quality.

Planned systems: Core, Platform, Render, Scene, Animation, Physics, Assets, Project, Editor and Export.

## REL
Versioned extensible asset container for meshes, materials, armatures, animations, morphs/shape keys, outfits and physics metadata. Optional sections must remain optional.

## SOL
Editable Soreal project/scene container preserving scene objects, REL references, animation tracks, physics configuration, cameras, lights, audio and render settings.
