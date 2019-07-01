# Gradle Plugin Migration Guide

The Thunderhead Android SDK 4.0 introduced a new dependency on the [Orchestration Gradle Plugin](ORCHESTRATION-PLUGIN-README.md), 
replacing the [Archinamon Gradle Plugin](https://github.com/Archinamon/android-gradle-aspectj) dependency.

#### Affects

This guide is for Thunderhead customers who plan on migrating from version(s) <= 3.0.0 to version(s) 4.0.0+.

#### Steps 

*Note: All code snippets are in groovy syntax.*

##### Using Legacy Gradle Plugin Architecture
* Update the application's `build.gradle` file.
  * Replace the `Archinamon` plugin with the `Orchestration` plugin.
    * Remove `apply plugin: 'com.archinamon.aspectj-ext'`
    * Add `apply plugin: 'com.thunderhead.android.orchestration-plugin'`
  * Remove the `aspectj` configuration block.
    * For `Interaction Studio` customers:
      ```groovy 
       aspectj {
           includeAspectsFromJar 'is-sdk'
           ajcArgs << '-Xlint:ignore'
       }
      ```
    * For `Thunderhead ONE` customers:
      ```groovy 
       aspectj {
           includeAspectsFromJar 'one-sdk'
           ajcArgs << '-Xlint:ignore'
       }
      ```
* Update the root project's `build.gradle` file.
  * Replace the `Archinamon` dependency with the `Orchestration` dependency.
    * Remove `classpath 'com.archinamon:android-gradle-aspectj:3.3.1'` from the `buildscript`'s `dependencies` block.
    * Add `classpath 'com.thunderhead.android:orchestration-plugin:1.0.0'` to the `buildscript`'s `dependencies` block.
  * Add the Thunderhead Maven Repository to the `buildscript`'s `repositories` block.
    ```groovy
      maven {
          name = "Thunderhead"
          url = uri("https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android")
      }
    ``` 
* You have successfully migrated the Thunderhead SDK required Gradle Plugins.

##### Using the Gradle Plugin Portal and `plugins` DSL
* Update the application's `build.gradle` file.
  * Replace the `Archinamon` plugin with the `Orchestration` plugin.
    * Remove `apply plugin: 'com.archinamon.aspectj-ext'`
    * Add `id 'com.thunderhead.android.orchestration-plugin' version '1.0.0'` to the `plugins` DSL block.
  * Remove the `aspectj` configuration block.
    * For `Interaction Studio` customers:
      ```groovy 
       aspectj {
           includeAspectsFromJar 'is-sdk'
           ajcArgs << '-Xlint:ignore'
       }
      ```
    * For `Thunderhead ONE` customers:
      ```groovy 
       aspectj {
           includeAspectsFromJar 'one-sdk'
           ajcArgs << '-Xlint:ignore'
       }
      ```
* Update the root project's `build.gradle` file.
  * Remove the `Archinamon` dependency.
    * Remove `classpath 'com.archinamon:android-gradle-aspectj:3.3.1'` from the `buildscript`'s `dependencies` block.
* You have successfully migrated the Thunderhead SDK required Gradle Plugins.

*See the main [README](README.md) for a complete example of a successfully configured application.*

*See the [Orchestration README](ORCHESTRATION-PLUGIN-README.md) for more details about the orchestration plugin.*