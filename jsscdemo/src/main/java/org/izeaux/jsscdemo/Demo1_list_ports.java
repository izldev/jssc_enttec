package org.izeaux.jsscdemo;
import jssc.SerialPortList;
public class Demo1_list_ports {

	// PAS REUSSI A FAIRE FONCTIONNER CETTE DEMO
	
	// Le package Ubuntu/Debian  libjssc-java est très vieux, bloqué en 2.8.0, version absente de Maven Central
	
		
    static {
        System.out.println("Demo1_list_ports: loading JSSC");
        try {
            System.load("/usr/lib/jni/libjSSC-2.8.so");
            System.out.println("Demo1_list_ports: loaded JSSC");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Demo1_list_ports: Native core library failed to load.\n" + e);
            System.exit(1);;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Demo1_list_ports: start");

        String[] portNames = SerialPortList.getPortNames();
        System.out.println("Demo1_list_ports: listing " + portNames.length + " port names");
        for(int i = 0; i < portNames.length; i++){
            System.out.println(portNames[i]);
        }

        System.out.println("Demo1_list_ports: end");
    }
}

/*
Demo1_list_ports: loading JSSC
Demo1_list_ports: loaded JSSC
Demo1_list_ports: start
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Exception in thread "main" java.lang.UnsatisfiedLinkError: Could not load the jssc library: Couldn't load library library jssc
	at jssc.SerialNativeInterface.<clinit>(SerialNativeInterface.java:126)
	at jssc.SerialPortList.<clinit>(SerialPortList.java:49)
	at org.izeaux.izlite.itfdmx.JSSCdemo.main(Demo1_list_ports.java:22)

*/