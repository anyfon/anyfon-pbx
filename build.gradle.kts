buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-allopen:${Deps.Version.Kotlin}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Deps.Version.Kotlin}")
        classpath("com.github.node-gradle:gradle-node-plugin:${Deps.Version.NodePlugin}")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${Deps.Version.SpringBoot}")
    }
}

allprojects {
    group = "ru.anyfon.pbx"
    version = "1.0.0"

    buildDir = File("${rootProject.rootDir}${File.separator}build${File.separator}${this.name}")

    val kotlinSrc = File("$projectDir${File.separator}src${File.separator}main${File.separator}kotlin")

    if (
        name.startsWith("web-") ||
        name.endsWith("-spring") ||
        name.endsWith("-r2dbc") ||
        name.endsWith("-domain") ||
        name.endsWith("-web") ||
        name.endsWith("-data")
    ) {
        if (!kotlinSrc.exists()) {
            val groupPath = File.separator + group.toString().replace(".", File.separator)
            File(kotlinSrc.toString() + groupPath).mkdirs()
        }
    }

    if (kotlinSrc.exists()) {
        plugins.apply("org.jetbrains.kotlin.jvm")
        dependencies {
            add("api", platform("org.jetbrains.kotlin:kotlin-bom:${Deps.Version.Kotlin}"))
            add("implementation", "org.jetbrains.kotlin:kotlin-reflect")
            add("implementation", "org.junit.jupiter:junit-jupiter:${Deps.Version.JUnitJupiter}")
        }
        tasks.named<Test>("test") {
            useJUnitPlatform()
        }
    }

    if (name.contains("-spring") || name.contains("-r2dbc") || name.contains("-web")) {
        plugins.apply("io.spring.dependency-management")
        plugins.apply("org.jetbrains.kotlin.plugin.spring")
        dependencies {
            add(
                "implementation",
                platform("org.springframework.boot:spring-boot-dependencies:${Deps.Version.SpringBoot}")
            )
        }
    }

    if (name.contains("data-r2dbc")) {
        dependencies {
            if (name != "common-data-r2dbc") {
                add("api", project(":common:common-data-r2dbc"))

                add("api", "org.springframework.data:spring-data-r2dbc")
                add("api", "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Deps.Version.KotlinCoroutines}")
                add("api", "org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Deps.Version.KotlinCoroutines}")

            }
        }
    }

    if (
        (name != "common-web" || !name.contains("js"))
        && name.contains("-web")
    ) {
        dependencies {
            add("api", project(":common:common-web"))
            //add("implementation", project(":web:web-app"))
        }
    }

    if (name.contains("-web-spring")) {
        dependencies {
            add("implementation", "org.springframework.boot:spring-boot-starter-webflux")
            add("implementation", "org.springframework.boot:spring-boot-starter-actuator")
        }
    }

    if (name != "common-domain" && name.contains("-domain")) {
        dependencies {
            add("api", project(":common:common-domain"))
        }
    }

    repositories {
        mavenCentral()
    }
}
