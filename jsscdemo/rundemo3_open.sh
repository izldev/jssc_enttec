
# run Demo3 : list available serial ports

if [ "$#" -ne 1 ]; then
   echo "one mandatory parameter : the USB device,   ex  /dev/ttyUSB0"
fi

PORT_DEVICE=$1

java -cp target/jsscdemo-0.0.1-SNAPSHOT-jar-with-dependencies.jar   org.izeaux.jsscdemo.Demo3_open_port  $PORT_DEVICE

