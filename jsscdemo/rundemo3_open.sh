
# run Demo3 : list available serial ports

# the jssc fat jar +  this application are binded in a single  enormous fat jar

mvn clean compile assembly:single

PORT_NAME=$1
java -cp target/jsscdemo-0.0.1-SNAPSHOT-jar-with-dependencies.jar   org.izeaux.jsscdemo.Demo3_open_port  $PORT_NAME

