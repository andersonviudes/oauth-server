import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    `java-library`
}

val confluentJson = project.properties["confluentJson"]

dependencies {
    implementation(project(":domain"))

    implementation("org.springframework.kafka:spring-kafka")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.confluent:kafka-json-serializer:$confluentJson")
    implementation("io.confluent:kafka-json-schema-serializer:$confluentJson")
}

tasks.withType<BootJar> {
    isEnabled = false
}
