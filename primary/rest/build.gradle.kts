import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    `java-library`
}

tasks.withType<BootJar> {
    isEnabled = false
}

dependencies {
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter-web"){
        exclude("org.springframework.boot",  "spring-boot-starter-tomcat")
    }

    implementation("org.springframework.boot:spring-boot-starter-jetty")
}