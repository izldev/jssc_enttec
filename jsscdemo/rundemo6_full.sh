
# run Demo6 

if [ "$#" -ne 1 ]; then
   echo "one mandatory parameter : the USB device,   ex  /dev/ttyUSB0"
   exit 12
fi

PORT_DEVICE=$1

java -cp target/jsscdemo-0.0.1-SNAPSHOT-jar-with-dependencies.jar   org.izeaux.jsscdemo.Demo6_full  $PORT_DEVICE

