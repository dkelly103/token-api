plugins {
	id("org.springframework.boot") version "2.6.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.0"
	kotlin("plugin.spring") version "1.6.0"
	id("org.web3j") version "4.8.9"
	id("org.web3j.solidity") version "0.3.2"
	id("com.github.node-gradle.node") version "3.1.1"
}

group = "com.github.dkelly103"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven (url = "https://plugins.gradle.org/m2/")
}

dependencies {

	//Kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	//Spring
	implementation("org.springframework.boot:spring-boot-starter-web:2.6.2")
	implementation("org.springframework.kafka:spring-kafka:2.7.6")

	//The rest: Organise alphabetically
	runtimeOnly("org.postgresql:postgresql:42.3.1")
	implementation("com.squareup.okhttp3:okhttp:4.9.3")
	implementation("org.web3j:core:4.8.9")
	implementation("org.web3j:besu:4.8.9")

	//Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test:2.6.2")
	testImplementation("org.springframework.kafka:spring-kafka-test:2.7.6")
}

tasks {

	node {
		nodeProjectDir.set(file("${project.projectDir}"))
	}

	web3j {
		generatedPackageName = "${group}.tokenapi.web3j"
		useNativeJavaTypes = true
		includedContracts = listOf("Token")
	}

	jar {
		enabled = true
		archiveClassifier.set("")
	}

	compileKotlin {
		dependsOn(":generateContractWrappers")
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "11"
		}
	}

	test {
		useJUnitPlatform()
	}
}