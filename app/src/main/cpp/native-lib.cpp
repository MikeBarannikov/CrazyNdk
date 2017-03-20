#include <jni.h>
#include <string>
#include "GaussianBlurFilter.h"
#include "SketchFilter.h"

#ifdef __cplusplus
extern "C" {
#endif

    JNIEXPORT jintArray JNICALL
    Java_com_crazy_ndk_crazyndk_filter_NativeFilterFunc_discreteGaussianBlur(JNIEnv *env, jclass object, jintArray pixels,
                                                                             jint width, jint height, jdouble sigma) {
        GaussianBlurOptions options(sigma);
        jintArray result = PROC_IMAGE_WITH_OPTIONS(env, pixels, width, height, GaussianBlurFilter, options);
        return result;
    }

    JNIEXPORT jintArray JNICALL
    Java_com_crazy_ndk_crazyndk_filter_NativeFilterFunc_sketchFilter(JNIEnv *env, jclass type, jintArray pixels,
                                                                     jint width, jint height) {
        jintArray result = PROC_IMAGE_WITHOUT_OPTIONS(env, pixels, width, height, SketchFilter);
        return result;
    }

    JNIEXPORT jstring JNICALL
    Java_com_crazy_ndk_crazyndk_MainActivity_stringFromJNI(
            JNIEnv *env,
            jobject /* this */) {
        std::string hello = "Hello from C++";
        return env->NewStringUTF(hello.c_str());
    }

#ifdef __cplusplus
}
#endif