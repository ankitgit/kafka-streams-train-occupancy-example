FROM openjdk:8

ENV B 'EMPTY'
ENV IT 'EMPTY'
ENV OT 'EMPTY'

ADD src/main/resources/application.properties  /opt/topology/application.properties
ADD build/libs/kafka-streams-train-occupancy-example-all.jar  /opt/topology/application.jar

CMD ["sh", "-c", "java -jar /opt/generator/application.jar -P /opt/generator/application.properties -B ${B} -IT ${IT} -OT ${OT}"]
