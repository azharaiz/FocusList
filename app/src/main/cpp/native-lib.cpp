#include <string.h>
#include <jni.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_id_ac_ui_cs_mobileprogramming_azharaiz_focuslist_fragments_SettingsFragment_00024Companion_helloFromJNI(
        JNIEnv *env, jobject, jstring input_name) {
    const char *name = env->GetStringUTFChars(input_name, NULL);
    char message[100] = "Hello, ";

    strcat(message, name);

    env->ReleaseStringUTFChars(input_name, name);
    return env->NewStringUTF(message);
}