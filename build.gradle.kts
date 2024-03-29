plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "org.dr4kn"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("arithmeticOperatorSolver.AppKt")
}