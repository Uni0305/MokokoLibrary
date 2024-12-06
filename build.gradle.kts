plugins {
    id("idea")
    id("java")
    id("maven-publish")
    id("co.uzzu.dotenv.gradle") version "4.0.0"
    id("com.gradleup.shadow") version "8.3.5"
    id("io.freefair.lombok") version "8.10.2"
    id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.2.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "me.uni0305"
version = "0.1.8"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.codemc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("com.google.code.gson:gson:2.11.0")
    compileOnly("com.zaxxer:HikariCP:6.0.0")
    compileOnly("org.mariadb.jdbc:mariadb-java-client:3.4.1")
    implementation("de.tr7zw:item-nbt-api:2.14.0")
}

idea {
    module.isDownloadJavadoc = true
    module.isDownloadSources = true
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

bukkitPluginYaml {
    main = "me.uni0305.mokoko.library.MokokoLibraryPlugin"
    apiVersion = "1.20"
    author = "Uni0305"
    description = "A library plugin for Uni0305's plugins."
    libraries = listOf(
        "com.google.code.gson:gson:2.11.0",
        "com.zaxxer:HikariCP:6.0.0",
        "org.mariadb.jdbc:mariadb-java-client:3.4.1"
    )
}

tasks.shadowJar {
    minimize()
    relocate("de.tr7zw.changeme.nbtapi", "me.uni0305.mokoko.shadow.nbtapi")
    archiveClassifier = ""
    if (env.isPresent("JAR_DIR"))
        destinationDirectory.set(file(env.fetch("JAR_DIR")))
}

tasks.runServer {
    minecraftVersion("1.20.4")
    if (env.isPresent("SERVER_JAR"))
        serverJar(file(env.fetch("SERVER_JAR")))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["shadow"])
        }
    }
}