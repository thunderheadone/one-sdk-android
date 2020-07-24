## Plugin Error Codes and Potential Solutions

#### 2007, Orchestration aspects jar file null. Check build.gradle configuration.
The 2007 error code is indicative of three possible problems: 

* A corrupt gradle cache
* A firewall blocking http downloads from `https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android`
* A problem with the Thunderhead Maven Repository `https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android`

**Possible solutions to a corrupt gradle cache**:

* Run gradle build with the `--refresh-dependencies` flag. 
  * Ex. `./gradlew assemble --refresh-dependencies`
* Clear gradle cache by removing the cache from your user's directory gradle dir. 
  * On unix systems: `rm -rf $HOME/.gradle/caches/`
  * Related [StackOverflow](https://stackoverflow.com/questions/13565082/how-can-i-force-gradle-to-redownload-dependencies)
* Search your gradle cache for the `android-aspects` artifact. If a `pom` is present but there is no related jar then clear your cache.
  * On unix systems: `find $HOME/.gradle -iname "*android-aspects*"` should list at least two files: 
  `android-aspects-VERSION.pom` and `android-aspects-VERSION.jar` where _VERSION_ can be any valid version. 
  
If the above solutions do not solve the problem please confirm your firewall allows access to 
`https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android`.  

If the gradle cache solutions do not solve the problem and the firewall is open please open a support ticket
by emailing [support](mailto:onesupport@thunderhead.com).

#### 1007, Detected gradle version: {a.b.c}. Version {x.y.z} required.
The 1007 error code is indicative of using an incompatible version of gradle in a gradle project that uses the Orchestration Plugin.

The Orchestration Plugin is compiled against a specific version of gradle and requires certain APIs to be available at runtime.
The Plugin therefore checks for gradle compatibility.

Gradle promises that all APIs will be [backwards compatible](https://docs.gradle.org/current/userguide/feature_lifecycle.html#backwards_compatibility)
until they are deprecated and removed, implying that using newer versions of gradle will be safe to use.

Google also supports open ended newer versions for gradle when using the Android Gradle Plugin.  Please refer to their [release notes](https://developer.android.com/studio/releases/gradle-plugin) which indicates the _minimum_ gradle version supported for each version of the Android Gradle Plugin.

For example, in the Android Gradle Plugin 3.4 release notes, under the [Update Gradle](https://developer.android.com/studio/releases/gradle-plugin#updating-gradle) section, the table shows the minimum supported version of gradle is `5.1.1+`.  In addition, the [release notes for 3.4.0](https://developer.android.com/studio/releases/gradle-plugin#3-4-0) specifically state `Gradle 5.1.1 or higher`.

**Solution to an incompatible Gradle Version**

Update the project's `gradle-wrapper.properties` file to use the specified version as indicated by the error message. This file is typically found under `projectRoot/gradle/wrapper`. 


