plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":pbx-manager:pbx-manager-domain"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    compileOnly("io.projectreactor:reactor-tools")

    implementation("org.springframework.data:spring-data-r2dbc")
    runtimeOnly("io.r2dbc:r2dbc-postgresql")
}

tasks.compileJava {
    options.release.set(8)
}
