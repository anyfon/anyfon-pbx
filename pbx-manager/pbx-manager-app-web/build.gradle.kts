plugins {
    id("org.springframework.boot")
}

dependencies {

    implementation("me.paulschwarz:spring-dotenv:2.5.3")

    implementation(project(":web:web-auth-domain"))
    implementation(project(":web:web-auth-data-r2dbc"))

    implementation(project(":pbx-manager:pbx-manager-domain"))
    implementation(project(":pbx-manager:pbx-manager-data-r2dbc"))

    implementation(project(":asterisk-api:asterisk-api-domain"))
    implementation(project(":asterisk-api:asterisk-api-data-r2dbc"))

    implementation(project(":web:web-starter-spring"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    compileOnly("io.projectreactor:reactor-tools")



    implementation("org.springframework.data:spring-data-r2dbc")

    runtimeOnly("io.r2dbc:r2dbc-postgresql")
    runtimeOnly("dev.miku:r2dbc-mysql")
}

tasks.compileJava {
    options.release.set(8)
}

tasks.build {
    dependsOn(":web:web-starter-spring:build")
}
