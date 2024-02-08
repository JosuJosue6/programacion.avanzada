plugins {
    id("java")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":listas"))
}

tasks.test {
    useJUnitPlatform()
}