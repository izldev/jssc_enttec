package org.izeaux.jsscdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jssc.SerialPort;
import jssc.SerialPortList;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class Demo6_full {

	  static byte DMX_PRO_MESSAGE_START = (byte) 0x7E; // 128
	  static byte DMX_PRO_MESSAGE_END   = (byte) 0xE7; // 231
	  static int DMX_PRO_MESSAGE_END_INT   =  0xE7; // 231
	  
	  static final byte WIDGET_GET_PARAMETERS   = (byte)  3;
	  static final byte WIDGET_SET_PARAMETERS   = (byte)  4;
	  static final byte WIDGET_SEND_PACKET      = (byte) 0x06;
	  static final byte WIDGET_GET_SERIALNUMBER = (byte) 10;
	
	private static final Logger log =  LoggerFactory.getLogger( Demo5_listen.class );

	
    public static void main(String[] args) {
    	System.out.println(" ");
    	System.out.println("**************************************");
    	System.out.println("Demo6_full    initializing...");
    	System.out.println("**************************************");

    	log.info(" log4j2 is working");
    	
    	String myportname=null;
        if (args.length < 1) {
            log.error("One argument required : serial port name");
        }  else {
        	myportname=args[0].trim();
        	log.info("Searching port '{}' ...",myportname);
        }
        
        Demo6_WidgetENTTEC widget = new Demo6_WidgetENTTEC(myportname);
        
        widget.open();
        
        byte[] jour = Utils.hexStringToBytes("FF FF FF FF FF FF FF FF");
        byte[] nuit = Utils.hexStringToBytes("00 00 00 00 00 00 00 00");
        
        // my first light show !
        for (int i=1;i<=10;i++) {
        	
			try { Thread.sleep(2000);	} catch (Exception e) {	}
			log.info("light up {}",i);
        	widget.sendDMXchannels(jour);
        	
			try { Thread.sleep(2000);	} catch (Exception e) {	}
			log.info("light down {},i");
        	widget.sendDMXchannels(nuit);
			
        }
	    // end of the show
        
        widget.close();
       
        
        log.info("Demo: end");
    } // main
    
    

	
}


