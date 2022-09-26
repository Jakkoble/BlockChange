plugins {
    kotlin("jvm") version "1.7.10"
    id("xyz.jpenilla.run-paper") version "1.0.6"
}

group = "de.jakkoble"
version = "1.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
    compileOnly("com.mojang:brigadier:1.0.18")
    implementation("net.axay:kspigot:1.19.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
}

tasks {
    runServer {
        minecraftVersion("1.19.2")
    }
}