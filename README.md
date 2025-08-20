# jssc_enttec

Some sample Java applications connecting to ENTTEC DMX USB PRO widget to send DMX data

using [ java-native / jssc ](https://github.com/java-native/jssc) project.

## Content

- Demo1 not working
- Demo2 lists all serial ports, tested with reals physical widgets
  ```
  $ cd jsscdemo
  $ ./rundemo2_list.sh
  **************************************
  Demo2_list_ports    initializing...
     scanning 1 port names (Demo3_open_port.java:32)
     0  :  /dev/ttyUSB0 (Demo3_open_port.java:38)
  ```
- Demo3 open/close a serial port

```
  $ cd jsscdemo
  $ ./rundemo3_open.sh /dev/ttyUSB0
    Trying to use port '/dev/ttyUSB0' ... (Demo3_open_port.java:46)
      trying to open (Demo3_open_port.java:50)
        isOpened=true (Demo3_open_port.java:58)
        isCTS=true (Demo3_open_port.java:59)
        isDSR=true (Demo3_open_port.java:60)
        . . .
```
