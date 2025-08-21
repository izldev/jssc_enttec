package org.izeaux.jsscdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortList;
import jssc.SerialPortException;

public class Demo6_WidgetENTTEC {

	static byte DMX_PRO_MESSAGE_START = (byte) 0x7E; // 128
	static byte DMX_PRO_MESSAGE_END = (byte) 0xE7; // 231
	static int DMX_PRO_MESSAGE_END_INT = 0xE7; // 231

	static final byte WIDGET_GET_PARAMETERS = (byte) 3;
	static final byte WIDGET_SET_PARAMETERS = (byte) 4;
	static final byte WIDGET_SEND_PACKET = (byte) 0x06;
	static final byte WIDGET_GET_SERIALNUMBER = (byte) 10;

	private static final Logger log = LoggerFactory.getLogger(Demo5_listen.class);

	private String deviceName = null;
	private SerialPort port = null;

	public Demo6_WidgetENTTEC(String devicename) {
		this.deviceName = devicename;
	}

	public void localize() {

		String[] portNames = SerialPortList.getPortNames();

		log.info("localize: scanning " + portNames.length + " port names");
		int found = -1111;
		for (int i = 0; i < portNames.length; i++) {
			if (portNames[i].equals(this.deviceName)) {
				found = i;
			}
			log.info("  number {}  : name = {}", i, portNames[i]);
		}

		if (found < 0) {
			log.error("Sorry, port '{}' was not found", this.deviceName);
			throw new RuntimeException("Device not found");
		} else {
			log.info("Port '{}' found", this.deviceName);
			this.port = new SerialPort(this.deviceName);
		}

	}// localize

	public boolean isLocalized() {
		return null != port;
	}

	public void open() {

		try {

			localize();

			port.openPort();

			log.info("      class={}", port.getClass());
			log.info("      getPortName={}", port.getPortName());
			log.info("      isOpened={}", port.isOpened());
			log.info("      isCTS={}", port.isCTS());
			log.info("      isDSR={}", port.isDSR());
			log.info("      isRING={}", port.isRING());
			log.info("      isRLSD={}", port.isRLSD());
			log.info("      getEventsMask={}", port.getEventsMask());
			log.info("      getFlowControlMode={}", port.getFlowControlMode());

			log.info("setting params...");
			port.setParams(SerialPort.BAUDRATE_57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;
			port.setEventsMask(mask);

			Demo6PortListener pl = new Demo6PortListener();
			port.addEventListener(pl);

			log.info("une petite pause....");
			try {
				Thread.sleep(50);
			} catch (Exception e) {
			}

			log.info("requesting Enttec widget serial number...");
			requestSerialNumber();

			log.info("une autre pause....");
			try {
				Thread.sleep(50);
			} catch (Exception e) {
			}
			log.info("requesting Enttec widget parameters...");
			requestParameters();

		} catch (Exception e) {
			log.error("Erreur grave ", e);
			e.printStackTrace();
		}

	}// open

	public void close() {

		log.info("Now closing port '{}' ...", deviceName);
		try {
			port.closePort();
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendDMXchannels(byte[] data) {

		data[0] = 0; // DMX command byte. No address 0 for DMX

		log.info("sendDMXchannels envoie {} bytes", data.length);
		sendEnttecMessage(WIDGET_SEND_PACKET, data);
	}

	/**
	 * helper method, send get_serial_number command to the box
	 */
	private void requestSerialNumber() {
		byte[] data = new byte[0];
		sendEnttecMessage(WIDGET_GET_SERIALNUMBER, data);
		return;
	}

	/**
	 * get the settings of the DMX pro and print them
	 */
	private void requestParameters() {
		byte[] data = new byte[0];
		sendEnttecMessage(WIDGET_GET_PARAMETERS, data);
	}

	/**
	 * send a dmx message to the widget
	 * 
	 * @param messageType
	 * @param data
	 */
	private void sendEnttecMessage(byte messageType, byte[] data) {

		log.info("sendEnttecMessage msgtype={} data:{}", messageType, Utils.byteArrayToHexStr(data));
		byte[] message;
		int dataSize = data.length;
		message = new byte[5 + dataSize];

		message[0] = DMX_PRO_MESSAGE_START;
		message[1] = messageType;
		message[2] = (byte) (dataSize & 255);
		message[3] = (byte) ((dataSize >> 8) & 255);

		if (dataSize > 0) {
			System.arraycopy(data, 0, message, 0 + 4, dataSize);
		}

		message[4 + dataSize] = DMX_PRO_MESSAGE_END;

		try {
			port.writeBytes(message);
		} catch (Exception e) {
			log.warn("sendEnttecMessage - Erreur :" + e, e);
		}
	}

	/* =============================== */

	public class Demo6PortListener implements SerialPortEventListener {

		// SerialPort port;
		public Demo6PortListener() {
			log.info("Demo6PortListener is created ");
		}

		public void serialEvent(SerialPortEvent event) {

			log.info("*** SerialPortEvent !");
			log.info("         getEventType  {}  ", event.getEventType());
			log.info("         getEventValue {}  ", event.getEventValue());
			log.info("         isBREAK       {}  ", event.isBREAK());
			log.info("         isCTS         {}  ", event.isCTS());
			log.info("         isDSR         {}  ", event.isDSR());
			log.info("         isERR         {}  ", event.isERR());
			log.info("         isRING        {}  ", event.isRING());
			log.info("         isRLSD        {}  ", event.isRLSD());
			log.info("         isRXCHAR      {}  ", event.isRXCHAR());
			log.info("         isRXFLAG      {}  ", event.isRXFLAG());
			log.info("         isTXEMPTY     {}  ", event.isTXEMPTY());

			if (event.isRXCHAR()) { // data is available
				try {
					int taille = event.getEventValue();
					log.info("  reading bytes... taille evt = {}", taille);
					log.info("  reading bytes... input buff = {}", port.getInputBufferBytesCount());

					byte[] buffer = port.readBytes(taille);

					log.info("  read {} bytes : {}", buffer.length, buffer);

					parsemsg(buffer);

				} catch (SerialPortException ex) {
					log.error("err", ex);
				}
			}

		}// serial event

		
		
		private void parsemsg(byte[] msg) {

			// check byte[0], expecting DMX_PRO_MESSAGE_START
			if (DMX_PRO_MESSAGE_START != msg[0]) {
				log.info("parsemsg     first byte is not DMX_PRO_MESSAGE_START");
			}

			// check last byte, expecting DMX_PRO_MESSAGE_END
			if (DMX_PRO_MESSAGE_END != msg[msg.length - 1]) {
				log.info("parsemsg     first byte is not DMX_PRO_MESSAGE_END");
			}

			// check byte[1] : message type
			byte msgtype = msg[1];
			log.info("parsemsgmessage type : {}", msgtype);

			// byte[2,3] = message size (lsb + msb)
			int msgsize = (int) (((msg[3] & 0xFF) << 8) | (msg[2] & 0xFF));
			log.info("parsemsgmessage size : {}", msgsize);

			switch (msgtype) {
			case 0x03:
				log.info("Widget parameters");
				log.info("fw rev MSB:{}" + msg[5]);
				log.info("fw rev LSB:{}" + msg[4]);
				log.info("Breaktime:{}" + msg[6]);
				log.info("Mark time:{}" + msg[7]);
				log.info("Update:{}" + msg[8]);
				break;

			case 0xA:
				log.info("Widget serial number");
				log.info("   {} {} {} {} " + msg[7] + msg[6] + msg[5] + msg[4]);
				break;

			default:
				log.info("Message type {} not used", msgtype);
				break;
			}//case

		}//parsemsg

	}// listener

}
