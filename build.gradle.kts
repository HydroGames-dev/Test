plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "1.7.1" apply false
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.starshootercity.originsfantasy"
version = "1.0.5"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation(project(":core"))
    implementation(project(":version"))
    implementation(project(":1.21", "reobf"))
}

tasks {
    compileJava {
        options.release.set(17)
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks.test {
    useJUnitPlatform()
}