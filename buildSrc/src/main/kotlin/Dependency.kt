import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope


fun DependencyHandlerScope.addTestImpl() {
    "testImplementation"("junit:junit:$junit_version")
    "androidTestImplementation"("androidx.test.ext:junit:$test_ext_version")
    "androidTestImplementation"("androidx.test.espresso:espresso-core:$test_espresso_version")
}

fun DependencyHandlerScope.addHiltImpl() {
    "implementation"("com.google.dagger:hilt-android:$hilt_version")
    "kapt"("com.google.dagger:hilt-android-compiler:$hilt_version")
}

fun PluginDependenciesSpecScope.addProjectPlugin() {
    id("com.android.application").version("7.2.1").apply(false)
    id("com.android.library").version("7.2.1").apply(false)
    id("org.jetbrains.kotlin.android").version("1.7.0").apply(false)
}

fun DependencyHandlerScope.addComposeBasic() {
    "implementation"("androidx.compose.ui:ui:$compose_version")
    "implementation"("androidx.compose.material3:material3:$material3_version")
    "implementation"("androidx.compose.ui:ui-tooling-preview:$compose_version")
    "androidTestImplementation"("androidx.compose.ui:ui-test-junit4:$compose_version")
    "debugImplementation"("androidx.compose.ui:ui-tooling:$compose_version")
    "debugImplementation"("androidx.compose.ui:ui-test-manifest:$compose_version")
}

fun DependencyHandlerScope.addComposeCompiler() {
     // "implementation"("androidx.compose.compiler:compiler:$compose_version")
     "implementation"("androidx.compose.runtime:runtime:$compose_version")
}