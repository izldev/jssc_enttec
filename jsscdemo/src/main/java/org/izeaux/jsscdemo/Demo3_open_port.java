package org.izeaux.jsscdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class Demo3_open_port {

	private static final Logger log =  LoggerFactory.getLogger( Demo2_list_ports.class );

    public static void main(String[] args) {
    	System.out.println(" ");
    	System.out.println("**************************************");
    	System.out.println("Demo3_open_port    initializing...");
    	System.out.println("**************************************");

    	log.info(" log4j2 is working");
    	
    	String myportname=null;
        if (args.length < 1) {
            log.error("One argument required : serial port name");
        }  else {
        	myportname=args[0].trim();
        	log.info("Searching port '{}' ...",myportname);
        }
        
        String[] portNames = SerialPortList.getPortNames();

        log.info(" scanning " + portNames.length + " port names");
        int found=-1111;
        for(int i = 0; i < portNames.length; i++){
        	if (portNames[i].equals(myportname)) {
        		found=i;
        	}
        	log.info("{}  :  {}", i, portNames[i]);
        }

    	if (found<0) {
    		if (null==myportname) log.error("Please provide the port name as parameter");
    		else
    		log.error("Sorry, port '{}' was not found",myportname);
    	} else {
    		log.info("Trying to use port '{}' ...",myportname);
    		
    		SerialPort port = new SerialPort(myportname); 
    		log.info("  Created port {}",port);
    		log.info("  trying to open");
    		
    		
    		try {
				port.openPort();
				
				log.info( "      class={}", port.getClass());
				log.info( "      getPortName={}", port.getPortName());
				log.info( "      isOpened={}", port.isOpened());
				log.info( "      isCTS={}", port.isCTS());
				log.info( "      isDSR={}", port.isDSR());
				log.info( "      isRING={}", port.isRING());
				log.info( "      isRLSD={}", port.isRLSD());
				log.info( "      getEventsMask={}", port.getEventsMask());
				log.info( "      getFlowControlMode={}", port.getFlowControlMode());
				
				log.info("Now closing port '{}' ...",myportname);

				port.closePort();
				
			} catch (Exception e) {
				log.error("Erreur grave ",e);
				e.printStackTrace();
			}
    		
    	}

        
        log.info("Demo3_open_port: end");
    }
}


