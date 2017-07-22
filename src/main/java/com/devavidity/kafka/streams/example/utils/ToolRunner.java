package com.devavidity.kafka.streams.example.utils;

import com.devavidity.kafka.streams.example.Application;
import org.apache.commons.cli.*;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.io.FileInputStream;
import java.util.Properties;

public abstract class ToolRunner {

    public static final String EMPTY_VALUE = "EMPTY";
    private final String MANDATORY_PROP_ARGUMENT = "P";
    private final String BOOT_STRAP_ARGUMENT = "B";
    private final String OUT_TOPIC = "OT";
    private final String IN_TOPIC = "IT";

    public static Properties properties = new Properties();

    public void execute(String[] args) {
        try {
            Options runTimeOptions = new Options();
            runTimeOptions.addRequiredOption(MANDATORY_PROP_ARGUMENT, "PropertyFile", true, "Location of the property file");
            runTimeOptions.addOption(BOOT_STRAP_ARGUMENT, true, "Boot Strap Server");
            runTimeOptions.addOption(OUT_TOPIC, true, "Output kafka topic");
            runTimeOptions.addOption(IN_TOPIC, true, "Input kafka topic");
            CommandLineParser cliParser = new DefaultParser();
            CommandLine parse = cliParser.parse(runTimeOptions, args);
            Option[] options = parse.getOptions();
            for (Option option : options) {
                if (option.getOpt().contentEquals(MANDATORY_PROP_ARGUMENT)) {
                    String propertyFileLocation = option.getValue(MANDATORY_PROP_ARGUMENT);
                    FileInputStream fileInputStream = new FileInputStream(propertyFileLocation);
                    properties.load(fileInputStream);
                } else if (!option.getValue().equals(EMPTY_VALUE)) {
                    switch (option.getOpt()) {
                        case BOOT_STRAP_ARGUMENT:
                            properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, option.getValue());
                            break;
                        case OUT_TOPIC:
                            properties.put(Application.KAFKA_TOPIC_OUTPUT, option.getValue());
                            break;
                        case IN_TOPIC:
                            properties.put(Application.KAFKA_TOPIC_INPUT, option.getValue());
                            break;
                    }
                }
            }
            run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    protected Properties getProperties() {
        return properties;
    }

    public abstract void run();

}
