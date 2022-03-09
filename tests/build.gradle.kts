import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.withType<BootJar> {
    isEnabled = false
}
