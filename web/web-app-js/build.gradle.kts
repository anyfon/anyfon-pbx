import com.github.gradle.node.yarn.task.YarnTask

plugins {
    id("com.github.node-gradle.node")
}

val buildTask = tasks.register<YarnTask>("buildAppJS") {
    this.group = "build"
    this.args.set(listOf("build"))
    this.dependsOn("yarn")
}
