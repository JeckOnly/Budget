pluginManagement {
//    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Budget"
include(":app")
include(":core:remote")
include(":core:model")
include(":core:datastore")
include(":core:database")
include(":core:data")
include(":core:designsystem")
include(":feature:example")
include(":feature:home")
include(":feature:chart")
include(":feature:more")
include(":core:navigation")
include(":util")
include(":app-addRecord")
