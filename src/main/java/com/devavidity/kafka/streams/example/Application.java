package com.devavidity.kafka.streams.example;

import com.devavidity.kafka.streams.example.processor.ComputeCompartmentLoad;
import com.devavidity.kafka.streams.example.utils.ToolRunner;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStreamBuilder;
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

        StringSerializer stringSerializer = new StringSerializer();
        StringDeserializer stringDeserializer = new StringDeserializer();

        properties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 100);
        // For illustrative purposes we disable record caches
        properties.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 2000);
        properties.put(StreamsConfig.CLIENT_ID_CONFIG, "Sample-Stateful-Processor");
        properties.put("group.id", "test-consumer-group6");
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "stateful_processor_id6");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "172.19.0.2:9092");
        properties.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, 1);

        //Build the topology
        TopologyBuilder builder = new TopologyBuilder();
        String inputTopic = properties.getProperty(KAFKA_TOPIC_INPUT_RAW);
        String outputTopic = properties.getProperty(KAFKA_TOPIC_OUTPUT_COMPARTMENT_LOAD);
        System.out.println(inputTopic);
        System.out.println(outputTopic);
        builder.addSource("SOURCE", stringDeserializer, stringDeserializer, inputTopic);
        builder.addProcessor("COMPUTE LOAD", ComputeCompartmentLoad::new, "SOURCE");
        builder.addSink("SINK", outputTopic, stringSerializer, stringSerializer,"COMPUTE LOAD");

        //Execute the topology
        KafkaStreams streams = new KafkaStreams(builder, properties);
        streams.start();
        System.out.println("now Start");
    }

}
