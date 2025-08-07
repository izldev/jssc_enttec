# jssc_enttec
Some sample Java applications connecting to ENTTEC DMX USB PRO widget to send DMX data

using  [ java-native / jssc ](https://github.com/java-native/jssc) project.

## Content
- Demo1 not working
- Demo2 seems to work, need more testing with reals physical widgets connected...

  ```
  $ cd jsscdemo
  $ mvn clean install
  $ ./rundemo2_list.sh
  **************************************
  Demo2_list_ports    initializing...
  **************************************
  [INFO ] org.izeaux.jsscdemo.Demo2_list_ports |  log4j2 is working (Demo2_list_ports.java:17)
  [INFO ] org.izeaux.jsscdemo.Demo2_list_ports |  getting ports names... (Demo2_list_ports.java:19)
  [DEBUG] org.scijava.nativelib.BaseJniExtractor | Not deleting leftover folder /tmp/nativelib-loader_1880178107007530059: is 6ms old (BaseJniExtractor.java:362)
  [DEBUG] org.scijava.nativelib.NativeLibraryUtil | processor is INTEL_64 os.arch is amd64 (NativeLibraryUtil.java:216)
  [DEBUG] org.scijava.nativelib.NativeLibraryUtil | architecture is LINUX_64 os.name is linux (NativeLibraryUtil.java:172)
  [DEBUG] org.scijava.nativelib.NativeLibraryUtil | architecture is LINUX_64 os.name is linux (NativeLibraryUtil.java:172)
  [DEBUG] org.scijava.nativelib.NativeLibraryUtil | platform specific path is natives/linux_64/ (NativeLibraryUtil.java:237)
  [DEBUG] org.scijava.nativelib.BaseJniExtractor | mappedLib is libjssc.so (BaseJniExtractor.java:362)
  [DEBUG] org.scijava.nativelib.BaseJniExtractor | URL is jar:file:/mnt/c/dom_ns/j2ssc/jssc_enttec/jsscdemo/target/jsscdemo-0.0.1-SNAPSHOT-jar-with-dependencies.jar!/natives/linux_64/libjssc.so (BaseJniExtractor.java:362)
  [DEBUG] org.scijava.nativelib.BaseJniExtractor | URL path is file:/mnt/c/dom_ns/j2ssc/jssc_enttec/jsscdemo/target/jsscdemo-0.0.1-SNAPSHOT-jar-with-dependencies.jar!/natives/linux_64/libjssc.so (BaseJniExtractor.java:362)
  [DEBUG] org.scijava.nativelib.BaseJniExtractor | Extracting 'jar:file:/mnt/c/dom_ns/j2ssc/jssc_enttec/jsscdemo/target/jsscdemo-0.0.1-SNAPSHOT-jar-with-dependencies.jar!/natives/linux_64/libjssc.so' to '/tmp/nativelib-loader_1880178107007530059/libjssc.so' (BaseJniExtractor.java:362)
  [INFO ] org.izeaux.jsscdemo.Demo2_list_ports |  got it (Demo2_list_ports.java:21)
  [INFO ] org.izeaux.jsscdemo.Demo2_list_ports |  now listing 0 port names (Demo2_list_ports.java:23)
  [INFO ] org.izeaux.jsscdemo.Demo2_list_ports | Demo2_list_ports: end (Demo2_list_ports.java:28)

  ```
