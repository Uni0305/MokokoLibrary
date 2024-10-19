# MokokoLibrary 

[![GitHub License](https://img.shields.io/github/license/Uni0305/MokokoLibrary?style=for-the-badge)](LICENSE.md)
[![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/Uni0305/MokokoLibrary/build.yml?style=for-the-badge)](https://github.com/Uni0305/MokokoLibrary/actions)
[![JitPack](https://img.shields.io/jitpack/version/com.github.Uni0305/MokokoLibrary?style=for-the-badge)](https://jitpack.io/#Uni0305/MokokoLibrary)

A library plugin for Uni0305's plugins.
Mokoko, included in this plug-in name, is the character and representative mascot of the game Lost Ark, developed and operated by Smilegate's group company Smilegate RPG, which is not related to this plugin.

## Installation
<details>
<summary>Maven</summary>

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
```xml
<dependencies>
    <dependency>
        <groupId>com.github.Uni0305</groupId>
        <artifactId>MokokoLibrary</artifactId>
        <version>VERSION</version>
    </dependency>
</dependencies>
```
</details>
<details>
<summary>Gradle (Groovy DSL)</summary>

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```
```groovy
dependencies {
    compileOnly 'com.github.Uni0305:MokokoLibrary:VERSION'
}
```
</details>
<details>
<summary>Gradle (Kotlin DSL)</summary>

```kts
repositories {
    maven("https://jitpack.io")
}
```
```kts
dependencies {
    compileOnly("com.github.Uni0305:MokokoLibrary:VERSION")
}
```
</details>