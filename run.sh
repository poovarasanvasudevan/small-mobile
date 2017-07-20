#!/usr/bin/env bash

./gradlew cleanLib -q

./gradlew cleanBundle -q

./gradlew buildLib -q

./gradlew buildBundle -q

/home/likewise-open/HTCINDIA/poovarasanv/android/android-sdk-linux/platform-tools/adb push /home/likewise-open/HTCINDIA/poovarasanv/gas/AndroidApp/SHPT2/app/build/outputs/apk/app-debug.apk /data/local/tmp/com.shpt

/home/likewise-open/HTCINDIA/poovarasanv/android/android-sdk-linux/platform-tools/adb shell pm install -r "/data/local/tmp/com.shpt"

/home/likewise-open/HTCINDIA/poovarasanv/android/android-sdk-linux/platform-tools/adb shell am start -n "com.shpt/com.shpt.MainActivity" -a android.intent.action.MAIN -c android.intent.category.LAUNCHER