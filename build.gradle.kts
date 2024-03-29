plugins {
    `java-library`
    id("eclipse")
    id("idea")
    id("xyz.jpenilla.run-paper") version "2.0.0"
}

group = "net.pl3x.guithium.test"
version = "1.0"
description = "Guithium Example Plugin"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

eclipse {
    classpath {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

repositories {
    mavenLocal()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.pl3x.net/public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT")
    compileOnly("net.pl3x.guithium:guithium-api:0.0.1-SNAPSHOT")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
        filesMatching("plugin.yml") {
            expand(
                "name" to rootProject.name,
                "group" to project.group,
                "version" to project.version,
                "description" to project.description
            )
        }
    }
    runServer {
        minecraftVersion("1.19.2")
    }
}
