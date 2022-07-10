import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("kapt") version "1.6.10"
}

group = "br.com.hellopizza"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    maven { url = uri("https://repo.spring.io/release") }
    mavenCentral()
}

dependencies {
    api("com.github.pozo:mapstruct-kotlin:1.4.0.0")
    kapt("com.github.pozo:mapstruct-kotlin-processor:1.4.0.0")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.mapstruct:mapstruct:1.5.1.Final")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.flywaydb:flyway-core")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    // https://mvnrepository.com/artifact/com.github.f4b6a3/uuid-creator
    implementation("com.github.f4b6a3:uuid-creator:4.6.1")

    // https://mvnrepository.com/artifact/io.github.microutils/kotlin-logging
    implementation("io.github.microutils:kotlin-logging:2.1.23")
    // https://mvnrepository.com/artifact/io.r2dbc/r2dbc-postgresql
    implementation("io.r2dbc:r2dbc-postgresql:0.8.12.RELEASE")
    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    // For flyway use
    implementation("org.postgresql:postgresql:42.3.6")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.1.Final")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
tasks.withType<JavaCompile> {
    options.compilerArgs = listOf("-Amapstruct.suppressGeneratorTimestamp=true")

}

tasks.withType<BootBuildImage> {
    builder = "paketobuildpacks/builder:tiny"
}
