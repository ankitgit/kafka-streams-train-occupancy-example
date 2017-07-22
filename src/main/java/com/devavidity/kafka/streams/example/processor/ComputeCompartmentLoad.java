package com.devavidity.kafka.streams.example.processor;

import com.devavidity.kafka.streams.example.models.Compartment;
import com.devavidity.kafka.streams.example.utils.LoadLevel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

import java.io.IOException;

public class ComputeCompartmentLoad implements Processor<String, String> {

    private ProcessorContext context;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ProcessorContext context) {
        this.context = context;
    }

    @Override
    public void process(String key, String value) {
        try {
            System.out.println(value);
            Compartment compartment = objectMapper.readValue(value, Compartment.class);
            int load = compartment.getIn() - compartment.getOut();
            int capacity = (load * 100 / compartment.getCapacity());

            if (capacity >= 70) {
                compartment.setLoadLevel(LoadLevel.HIGH);
            } else if (capacity >= 40) {
                compartment.setLoadLevel(LoadLevel.MEDIUM);
            } else {
                compartment.setLoadLevel(LoadLevel.LOW);
            }

            String newKey = compartment.getVehicleId() + "-" + compartment.getCompartmentId();
            String newValue = compartment.toJson();
            context.forward(newKey, newValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void punctuate(long timestamp) {
        //Nothing to do
    }

    @Override
    public void close() {

    }
}
