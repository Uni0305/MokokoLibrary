plugins {
    `java-library`
    id("co.uzzu.dotenv.gradle") version "4.0.0"
    id("com.gradleup.shadow") version "8.3.5"
}

group = "me.uni0305"
version = "0.2.0"

dependencies {
    implementation(project(":mokokolibrary-bukkit", "shadow"))
}

tasks.shadowJar {
    archiveClassifier = ""
    if (env.isPresent("JAR_DIR"))
        destinationDirectory.set(file(env.fetch("JAR_DIR")))
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
