pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Homeworks"
include(":app")
include(":m1_hello_world")
include(":m2_layout")
include(":m3_constraint")
include(":m4_components")
include(":m5_quiz_resources")
include(":m7_quiz_fragments")
include(":m8_quiz_animation")
