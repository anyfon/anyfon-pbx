dependencies {
    implementation(project(":common:common-web"))
    api("org.springframework.boot:spring-boot-starter-security")
    api("org.springframework.boot:spring-boot-starter-webflux")
    api("org.springframework.boot:spring-boot-starter-actuator")
}

val appBuildDistPath = File(
    "$rootDir${File.separator}" +
            "build${File.separator}" +
            "web-app-js${File.separator}" +
            "dist"
)


val staticResourcePath = File(
    "${layout.projectDirectory}${File.separator}" +
            "src${File.separator}" +
            "main${File.separator}" +
            "resources${File.separator}" +
            "static${File.separator}" +
            "app"
)

var copyAppTask = tasks.register<Copy>("_copyAppJS") {
    group = "build"
    dependsOn(":web:web-app-js:buildAppJS")
    doFirst {
        staticResourcePath.delete()
    }
    from(appBuildDistPath)
    into(staticResourcePath)
}

tasks.register("_buildWithAppJS") {
    group = "build"
    dependsOn("build", "_copyAppJS")
}

tasks.clean {
    doFirst {
        staticResourcePath.delete()
    }
}
