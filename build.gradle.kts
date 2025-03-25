plugins {
    java
    id("com.gradleup.shadow") version "9.0.0-beta4"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

group = "de.sekuramis"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.mysql:mysql-connector-j:9.2.0")
    implementation("net.dv8tion:JDA:5.1.2")
    implementation("com.zaxxer:HikariCP:6.2.1")
    implementation("org.yaml:snakeyaml:2.4")

    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
}

tasks.assemble {
    dependsOn(tasks.shadowJar)
}