val kafkaJsonSerializer = project.properties["kafkaJsonSerializer"]
val jaxbImpl = project.properties["jaxbImpl"]

dependencies {

    implementation(project(":domain").dependencyProject.sourceSets.main.get().output)
    implementation(project(":rest").dependencyProject.sourceSets.main.get().output)
    implementation(project(":persistence").dependencyProject.sourceSets.main.get().output)
    implementation(project(":consumer").dependencyProject.sourceSets.main.get().output)


    implementation(project(":domain"))
    implementation(project(":rest"))
    implementation(project(":persistence"))
    implementation(project(":consumer"))

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("com.sun.xml.bind:jaxb-impl:$jaxbImpl")

}

tasks {
    bootJar {
        enabled = true

        archiveBaseName.set("app")
        archiveAppendix.set("")
        archiveVersion.set("")
        archiveClassifier.set("")
    }
}
