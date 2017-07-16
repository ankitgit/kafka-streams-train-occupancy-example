package com.devavidity.kafka.streams.example.processor;

import com.devavidity.kafka.streams.example.models.Compartment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

import java.io.IOException;

public class ComputeCompartmentLoad implements Processor<String, String> {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ProcessorContext context) {

    }

    @Override
    public void process(String key, String value) {
        try {

            Compartment compartment = objectMapper.readValue(value, Compartment.class);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void punctuate(long timestamp) {

    }

    @Override
    public void close() {

    }
}
