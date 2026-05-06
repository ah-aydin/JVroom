import org.jetbrains.kotlin.gradle.dsl.JvmTarget

    plugins {
      java
      kotlin("jvm") version "2.2.0"
      id("org.jetbrains.intellij.platform") version "2.10.5"
    }

group = "com.ofya"
version = "2026-05-06"

repositories {
  mavenCentral()

  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdea("2026.1")
    bundledPlugin("com.intellij.java")
  }
}


java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(17))
  }
}

tasks {
  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
  }

  patchPluginXml {
    sinceBuild.set("261.0")
    untilBuild.set("261.*")
  }

  signPlugin {
    certificateChainFile.set(file("../keys/chain.crt"))
    privateKeyFile.set(file("../keys/private.pem"))
    password.set("nope")
  }

  publishPlugin {
    token.set("nope")
  }
}
