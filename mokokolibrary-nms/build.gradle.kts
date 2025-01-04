plugins {
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.11" apply false
}

subprojects {
    apply {
        plugin("io.papermc.paperweight.userdev")
    }

    dependencies {
        implementation(project(":mokokolibrary-common"))
    }
}
