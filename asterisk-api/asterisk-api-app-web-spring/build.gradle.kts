dependencies {
    implementation(project(":asterisk-api:asterisk-api-domain"))
    implementation(project(":asterisk-api:asterisk-api-data-r2dbc"))
    implementation("me.paulschwarz:spring-dotenv:2.5.3")
    runtimeOnly("dev.miku:r2dbc-mysql")
}
