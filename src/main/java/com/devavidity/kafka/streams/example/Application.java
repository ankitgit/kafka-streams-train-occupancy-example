package com.devavidity.kafka.streams.example;

import com.devavidity.kafka.streams.example.processor.ComputeCompartmentLoad;
import com.devavidity.kafka.streams.example.utils.ToolRunner;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.processor.TopologyBuilder;

import java.util.Properties;

public class Application extends ToolRunner {

    public static final String KAFKA_TOPIC_OUTPUT_COMPARTMENT_LOAD = "kafka.topic.output.compartment.load";
    private final String KAFKA_TOPIC_INPUT_RAW = "kafka.topic.input.raw";

    public static void main(String[] args) {
        Application application = new Application();
        application.execute(args);
    }

    @Override
    public void run() {
        Properties properties = getProperties();

        //Build the topology
        TopologyBuilder builder = new TopologyBuilder();
        builder.addSource("SOURCE", properties.getProperty(KAFKA_TOPIC_INPUT_RAW));
        builder.addProcessor("COMPUTE LOAD", ComputeCompartmentLoad::new, "SOURCE");
        builder.addSink("SINK", KAFKA_TOPIC_OUTPUT_COMPARTMENT_LOAD, "COMPUTE LOAD");

        //Execute the topology
        KafkaStreams streams = new KafkaStreams(builder, properties);
        streams.start();
    }

}
