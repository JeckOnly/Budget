// https://docs.gradle.org/current/userguide/kotlin_dsl.html#sec:kotlin-dsl_plugin
plugins {
    `kotlin-dsl`// 提供了kotlin的标准库，和kotlin kts的拓展等支持
    // `kotlin-dsl-precompiled-script-plugins`// 把kts脚本变成插件
}

repositories {
    mavenCentral()
}