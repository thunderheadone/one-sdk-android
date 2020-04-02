# Thunderhead Orchestration Plugin

Thunderhead Gradle Plugin for augmenting an Android Application at build time.

Requires Gradle 5.2.1+

### Apply to project

- Using the `plugins` DSL
  - Kotlin Script
  
    ```kotlin 
      // build.gradle.kts 
      plugins {
        id("com.thunderhead.android.orchestration-plugin") version "1.0.1"
      }
    ```
    
  - Groovy Script
  
    ```groovy
    // build.gradle
    plugins {
        id 'com.thunderhead.android.orchestration-plugin' version '1.0.1'
    }
    ```
    
- Using the legacy `buildscript` DSL
  - Kotlin Script
  
    ```kotlin
    buildscript {
        repositories {
            google()
            jcenter()
            maven {
                name = "Thunderhead"
                url = uri("https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android")
            }
        }
        dependencies {
            classpath("com.thunderhead.android:orchestration-plugin:1.0.1")
        }
    }
    apply(plugin = "com.thunderhead.android:orchestration-plugin")
    ```
        
  - Groovy Script
    
    ```groovy
    buildscript {
        repositories {
            google()
            jcenter()
            maven {
                name 'Thunderhead'
                url = 'https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android'
            }
        }
        dependencies {
            classpath 'com.thunderhead.android:orchestration-plugin:1.0.1'
        }
    }
    apply plugin: 'com.thunderhead.android.orchestration-plugin'
    ```

### DSL API Reference
  - Kotlin Script
  
```kotlin
// build.gradle.kts
thunderhead {
    enabled.set(true) // default true
    resources {
      buildDirectory.set(File("$buildDir/test")) // default buildDir/orchestration/resources
    }
    classProcessing {
      logfile.set(File("$buildDir/test/custom.log")) // defaults to buildDir/orchestration/classProccessorLog.log
      javaSourceTarget.set(JavaVersion.VERSION_1_8) // defaults to 1.7
      javaOutputTarget.set(JavaVersion.VERSION_1_8)  // defaults to 1.7
      processMainConfiguration.set(true) // default true
      processTestConfiguration.set(true) // default true
    }
}
```

- Groovy Script
```groovy
// build.gradle
thunderhead {
    enabled = true // default true
    resources {
      buildDirectory = new File("$buildDir/test") // default buildDir/orchestration/resources
    }
    classProcessing {
      logfile = new File("$buildDir/test/custom.log") // defaults to buildDir/orchestration/classProccessorLog.log
      javaSourceTarget = JavaVersion.VERSION_1_8 // defaults to 1.7
      javaOutputTarget = JavaVersion.VERSION_1_8  // defaults to 1.7
      processMainConfiguration = true // default true
      processTestConfiguration = true // default true
    }
}
```

