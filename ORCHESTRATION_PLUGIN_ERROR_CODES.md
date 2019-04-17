## Plugin Error Codes and Potential Solutions

#### 2007, Orchestration aspects jar file null. Check build.gradle configuration.
The 2007 error code is indicative of three possible problems: 

* A corrupt gradle cache
* A firewall blocking http downloads from `https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android`
* A problem with the Thunderhead Maven Repository `https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android`

**Possible solutions to a corrupt gradle cache**:

* Run gradle build with the `--refresh-dependencies` flag. 
  * Ex. `/.gradlew assemble --refresh-dependencies`
* Clear gradle cache by removing the cache from your user's directory gradle dir. 
  * On unix systems: `rm -rf $HOME/.gradle/caches/`
  * Related [StackOverflow](https://stackoverflow.com/questions/13565082/how-can-i-force-gradle-to-redownload-dependencies)
* Search your gradle cache for the `android-aspects` artifact. If a `pom` is present but there is no related jar then clear your cache.
  * On unix systems: `find $HOME/.gradle -iname "*android-aspects*"` should list at least two files: 
  `android-aspects-VERSION.pom` and `android-aspects-VERSION.jar` where _VERSION_ can be any valid version. 
  
If the above solutions do not solve the problem please confirm with your firewall allows access to 
`https://thunderhead.mycloudrepo.io/public/repositories/one-sdk-android`.  

If the gradle cache solutions do not solve the problem and the firewall is open please open a support ticket
by emailing [support](mailto:onesupport@thunderhead.com).