package org.izeaux.jsscdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

public class Demo5PortListener implements SerialPortEventListener {

	private static final Logger log =  LoggerFactory.getLogger( Demo5PortListener.class );
	
    SerialPort port;
    public Demo5PortListener(SerialPort port) {
        this.port = port;
        log.info("Demo5PortListener is created on portname {} ",port.getPortName());
    }
    
    public void serialEvent(SerialPortEvent event) {
    	
    	log.info("*** SerialPortEvent !");
    	log.info("         getEventType  {}  ",event.getEventType());
    	log.info("         getEventValue {}  ",event.getEventValue());
    	log.info("         isBREAK       {}  ",event.isBREAK());
    	log.info("         isCTS         {}  ",event.isCTS());
    	log.info("         isDSR         {}  ",event.isDSR());
    	log.info("         isERR         {}  ",event.isERR());
    	log.info("         isRING        {}  ",event.isRING());
    	log.info("         isRLSD        {}  ",event.isRLSD() );
    	log.info("         isRXCHAR      {}  ",event.isRXCHAR() );
    	log.info("         isRXFLAG      {}  ",event.isRXFLAG() );
    	log.info("         isTXEMPTY     {}  ",event.isTXEMPTY() );
    	
    }

}
