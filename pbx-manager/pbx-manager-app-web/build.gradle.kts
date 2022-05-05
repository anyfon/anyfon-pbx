plugins {
    id("org.springframework.boot")
}

dependencies {

    api(project(":web:web-auth-domain"))
    api(project(":web:web-auth-data-r2dbc"))

    implementation(project(":pbx-manager:pbx-manager-domain"))

    implementation(project(":web:web-starter-spring"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    compileOnly("io.projectreactor:reactor-tools")



    implementation("org.springframework.data:spring-data-r2dbc")
    runtimeOnly("io.r2dbc:r2dbc-postgresql")
}

tasks.compileJava {
    options.release.set(8)
}

tasks.build {
    dependsOn(":web:web-starter-spring:build")
}
