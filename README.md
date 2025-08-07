# jssc_enttec
Some sample Java applications connecting to ENTTEC DMX USB PRO widget to send DMX data

using  [ java-native / jssc ](https://github.com/java-native/jssc) project.

## Content
- Demo1 not working
- Demo2 seems to work, need more testing with reals physical widgets connected...

  ```
     $ cd jsscdemo
     $ mvn clean install
     $ ./rundemo2.sh
       Demo1_list_ports: getting ports names...
       SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
       SLF4J: Defaulting to no-operation (NOP) logger implementation
       SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
       Demo1_list_ports: got it
       Demo1_list_ports: listing 0 port names
       Demo1_list_ports: end
  ```
