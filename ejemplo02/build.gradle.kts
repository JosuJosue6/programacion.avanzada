plugins {
    id("java")
    id("application")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":ejemplo01"))
}

tasks.test {
    useJUnitPlatform()
}