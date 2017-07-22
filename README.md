# kafka-streams-train-occupancy-example
A basic example of kafka stream application for computing what is the compartment load of a running train.
Its just a play app to learn a bit about kafka stream.

# How to run
## running a fat jar locally
* Use gradle shadow target to generate the executable jar file, i.e.: gradle clean shadowJar.
* An executable jar can be found on build/libs/kafka-streams-train-occupancy-example-all.jar
* Run: java -jar kafka-streams-train-occupancy-example-all.jar -P application.properties
* Note: an example application.properties is located in main resources folder.
* Or: java -jar kafka-streams-train-occupancy-example-all.jar -P application.properties -B 0.0.0.0:9092 -IT input_topic -OT output_topic.
* Note: If you want to override the default properties in application.properties you can use the flag -B for boot server, -IT for input topic, -OT for output topic.

## Running via docker image
* execute build.sh
* Run: docker run -d -e B=[bootstrap server address] -e IT=[input Topic name] -e OT=[output topic name] --name [name of the container] train-occupancy-example
* Note: The environment variables are completely optional
