//
// Created by Amirul on 7/2/2019 AD.
//

#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_chatapplication_view_MainActivity_getNativeKey1(JNIEnv *env, jobject instance) {

 return (*env)->  NewStringUTF(env, "TmF0aXZlNWVjcmV0UEBzc3cwcmQx");
}

JNIEXPORT jstring JNICALL
Java_com_chatapplication_view_MainActivity_getNativeKey2(JNIEnv *env, jobject instance) {

 return (*env)->NewStringUTF(env, "TmF0aXZlNWVjcmV0UEBzc3cwcmQy");
}