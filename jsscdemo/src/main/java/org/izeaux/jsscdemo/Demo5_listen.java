package org.izeaux.jsscdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jssc.SerialPort;
import jssc.SerialPortList;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class Demo5_listen {

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
    	System.out.println("Demo5_listen    initializing...");
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

				log.info( "setting params...");
				port.setParams(SerialPort.BAUDRATE_57600 , SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

				int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;
				port.setEventsMask(mask);
				
				Demo5PortListener pl = new Demo5PortListener(port);
				port.addEventListener(pl );
				
				log.info( "une petite pause....");
				try {
					Thread.sleep(50);
				} catch (Exception e) {	}
				
				log.info( "requesting serial number...");
				requestSerialNumber(port);
				
				log.info( "requesting Enttec widget parameters...");
				requestParameters(port);
				
				log.info("Now closing port '{}' ...",myportname);
				port.closePort();
				
			} catch (Exception e) {
				log.error("Erreur grave ",e);
				e.printStackTrace();
			}
    		
    	}

        
        log.info("Demo: end");
    } // main
    
    
    
    
	/**
	 * helper method, send get_serial_number command to the box 
	 */
	private static void requestSerialNumber(SerialPort mySerialPort){
	  byte[] data = new byte[0];
	  sendEnttecMessage(mySerialPort, WIDGET_GET_SERIALNUMBER, data);
	  return;
	  }
	
	/**
	 * get the settings of the DMX pro and print them 
	 */
	private static void requestParameters(SerialPort mySerialPort){
	  byte[] data = new byte[0];
	  sendEnttecMessage(mySerialPort, WIDGET_GET_PARAMETERS, data);	
	  }
	
	
	

	/** 
	 * send a dmx message to the widget
	 * @param messageType
	 * @param data
	 */
	private static void sendEnttecMessage( SerialPort mySerialPort, byte messageType, byte[] data ){

	  byte[] message;
	  int dataSize = data.length;
	  message = new byte[5 + dataSize];
	   
	  message[0] = DMX_PRO_MESSAGE_START;
	  message[1] = messageType;
	  message[2] = (byte) (dataSize & 255);
	  message[3] = (byte) ((dataSize >> 8) & 255);

   
	  if(dataSize >0){ 
	  System.arraycopy(data, 0, message, 0+4, dataSize);
	  }
	  
	  message[4 + dataSize] = DMX_PRO_MESSAGE_END;
	  
	  //log.debug("sendDmxMessage flux="+OutilsGen.flux2hexString(message, 50)+"...");
	  
	  try{
		mySerialPort.writeBytes(message);

	    }
	  catch(Exception e){
		log.warn("sendDmxMessage - Erreur :"+e,e);
	    }
	  }


	
}


