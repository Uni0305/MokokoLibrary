plugins {
    id("idea")
    id("java")
    id("maven-publish")
    id("co.uzzu.dotenv.gradle") version "4.0.0"
    id("io.freefair.lombok") version "8.10.2"
    id("io.papermc.paperweight.userdev") version "1.7.3"
    id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.2.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "me.uni0305"
version = "0.1.6"

repositories {
    mavenCentral()
}

dependencies {
    paperweight.paperDevBundle("1.20.4-R0.1-SNAPSHOT")
    compileOnly("com.google.code.gson:gson:2.11.0")
    compileOnly("com.zaxxer:HikariCP:6.0.0")
    compileOnly("org.mariadb.jdbc:mariadb-java-client:3.4.1")
}

idea {
    module.isDownloadJavadoc = true
    module.isDownloadSources = true
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
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

tasks.assemble {
    dependsOn(tasks.reobfJar)
}

tasks.reobfJar {
    if (env.isPresent("JAR_DIR"))
        outputJar = File(env.fetch("JAR_DIR"), "${project.name}-${project.version}.jar")
}

tasks.runServer {
    minecraftVersion("1.20.4")
    if (env.isPresent("SERVER_JAR"))
        serverJar(file(env.fetch("SERVER_JAR")))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(tasks.reobfJar)
        }
    }
}