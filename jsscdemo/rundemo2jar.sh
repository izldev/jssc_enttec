
# run Demo2 : list available serial ports

# the jssc fat jar +  this application are binded in a single  enormous fat jar

mvn clean compile assembly:single


java -cp target/jsscdemo-0.0.1-SNAPSHOT-jar-with-dependencies.jar   org.izeaux.jsscdemo.Demo2_list_ports

