plugins {
    `java-library`
    id("com.gradleup.shadow") version "8.3.5"
}

group = "me.uni0305"
version = "0.1.7"

dependencies {
    implementation(project(":mokokolibrary-bukkit", "shadow"))
}

tasks.shadowJar {
    archiveClassifier = ""
}

allprojects {
    apply {
        plugin("java")
        plugin("com.gradleup.shadow")
    }

    repositories {
        mavenCentral()
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }

    tasks.shadowJar {
        exclude("META-INF/**")
    }
}

subprojects {
    group = rootProject.group
    version = rootProject.version

    tasks.shadowJar {
        minimize()
    }
}
