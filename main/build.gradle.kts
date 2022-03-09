import org.springframework.boot.gradle.tasks.bundling.BootJar

val kafkaJsonSerializer = project.properties["kafkaJsonSerializer"]

dependencies {

    implementation(project(":domain").dependencyProject.sourceSets.main.get().output)
    implementation(project(":rest").dependencyProject.sourceSets.main.get().output)
    implementation(project(":persistence").dependencyProject.sourceSets.main.get().output)


    implementation(project(":domain"))
    implementation(project(":rest"))
    implementation(project(":persistence"))

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.boot:spring-boot-starter-web")

}

tasks.withType<BootJar> {
    isEnabled = true
}
