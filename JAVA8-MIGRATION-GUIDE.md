# Java 8 Migration

The Thunderhead SDK 5.0.0+ targets the Java8 Language Specification.  This requires that consuming applications also target Java 8.

#### Affects

All applications previously integrated with the Thunderhead SDK <= 5.0.0 that were not already targeting Java 8 or
if you encounter an error message during the Gradle build that contains the following message:

```
The dependency contains Java 8 bytecode. Please enable desugaring by adding the following to build.gradle
   android {
       compileOptions {
           sourceCompatibility 1.8
           targetCompatibility 1.8
       }
   }
```

#### Steps to fix

Please place the recommended code into your _app_ level `build.gradle` file in the `android` configuration closure as shown above or in the error message.

Recommended code:
```groovy
compileOptions {
   sourceCompatibility 1.8
   targetCompatibility 1.8
}
```
