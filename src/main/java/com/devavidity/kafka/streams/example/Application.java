package com.devavidity.kafka.streams.example;

import com.devavidity.kafka.streams.example.processor.ComputeCompartmentLoad;
import com.devavidity.kafka.streams.example.utils.ToolRunner;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.processor.TopologyBuilder;

import java.util.Properties;

public class Application extends ToolRunner {

    public static final String KAFKA_TOPIC_OUTPUT = "kafka.topic.output";
    public static final String KAFKA_TOPIC_INPUT = "kafka.topic.input";

    public static void main(String[] args) {
        Application application = new Application();
        application.execute(args);
    }

    @Override
    public void run() {
        Properties properties = getProperties();

        StringSerializer stringSerializer = new StringSerializer();
        StringDeserializer stringDeserializer = new StringDeserializer();

        //Build the topology
        TopologyBuilder builder = new TopologyBuilder();
        String inputTopic = properties.getProperty(KAFKA_TOPIC_INPUT);
        String outputTopic = properties.getProperty(KAFKA_TOPIC_OUTPUT);
        builder.addSource("SOURCE", stringDeserializer, stringDeserializer, inputTopic);
        builder.addProcessor("COMPUTE LOAD", ComputeCompartmentLoad::new, "SOURCE");
        builder.addSink("SINK", outputTopic, stringSerializer, stringSerializer,"COMPUTE LOAD");

        //Execute the topology
        KafkaStreams streams = new KafkaStreams(builder, properties);
        streams.start();
    }

}
