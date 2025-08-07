package org.izeaux.jsscdemo;

import jssc.SerialPortList;

public class Demo2_list_ports {


    public static void main(String[] args) {
        System.out.println("Demo2_list_ports: getting ports names...");

        String[] portNames = SerialPortList.getPortNames();
        System.out.println("Demo2_list_ports: got it");
        System.out.println("Demo2_list_ports: listing " + portNames.length + " port names");
        for(int i = 0; i < portNames.length; i++){
            System.out.println(portNames[i]);
        }

        System.out.println("Demo2_list_ports: end");
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
