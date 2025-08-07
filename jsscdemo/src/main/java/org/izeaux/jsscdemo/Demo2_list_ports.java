package org.izeaux.jsscdemo;

import jssc.SerialPortList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo2_list_ports {

	private static final Logger log =  LoggerFactory.getLogger( Demo2_list_ports.class );

    public static void main(String[] args) {
    	System.out.println(" ");
    	System.out.println("**************************************");
    	System.out.println("Demo2_list_ports    initializing...");
    	System.out.println("**************************************");
    	
    	log.info(" log4j2 is working");
    	
    	log.info(" getting ports names...");
        String[] portNames = SerialPortList.getPortNames();
        log.info(" got it");
        
        log.info(" now listing " + portNames.length + " port names");
        for(int i = 0; i < portNames.length; i++){
        	log.info("{}  :  {}", i, portNames[i]);
        }

        log.info("Demo2_list_ports: end");
    }
}

/*

Attention :
récupérer le JAR de JSSC dans la release GitHub  https://github.com/java-native/jssc/releases/download/v2.10.2/jssc-2.10.2.jar
et non celui du dépot Maven Central

Sur Github, c'est un "FAT JAR" qui intègre les dépendances transitives scijava.nativelib et slf4j
=> renommé jssc-2.10.2-fat.jar et copié dans src/main/resources

Test :

$ java -cp target/classes/:target/classes/jssc-2.10.2-fat.jar org.izeaux.jsscdemo.Demo2_list_ports


*/
