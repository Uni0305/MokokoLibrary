plugins {
    `maven-publish`
    id("io.freefair.lombok") version "8.11"
    id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.2.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.5.1")
    implementation("com.zaxxer:HikariCP:6.2.1")
    implementation(project(":mokokolibrary-common"))
    implementation(project(":mokokolibrary-nms:1_19_r3", "reobf"))
    implementation(project(":mokokolibrary-nms:1_20_r1", "reobf"))
    implementation(project(":mokokolibrary-nms:1_20_r2", "reobf"))
    implementation(project(":mokokolibrary-nms:1_20_r3", "reobf"))
    implementation(project(":mokokolibrary-nms:1_20_r4", "reobf"))
    implementation(project(":mokokolibrary-nms:1_21_r1", "reobf"))
    implementation(project(":mokokolibrary-nms:1_21_r2", "reobf"))
    implementation(project(":mokokolibrary-nms:1_21_r3", "reobf"))
}

bukkitPluginYaml {
    name = rootProject.name
    main = "me.uni0305.mokoko.library.bukkit.MokokoLibraryPlugin"
    apiVersion = "1.20"
    author = "Uni0305"
    description = "A library plugin for Uni0305's plugins."
}

tasks.runServer {
    minecraftVersion("1.20.4")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}