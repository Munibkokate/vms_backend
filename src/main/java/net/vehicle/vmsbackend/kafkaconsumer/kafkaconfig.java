package net.vehicle.vmsbackend.kafkaconsumer;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import java.util.Properties;

//Enable Kafka support in the Spring application
@EnableKafka
//Marks this class as a configuration class
@Configuration
public class kafkaconfig {

	 // Defining a bean for creating a StreamsBuilder instance
    @Bean
     StreamsBuilder streamsBuilder() {
    	 // Creating a new StreamsBuilder instance
        StreamsBuilder builder = new StreamsBuilder();
        // Define a stream from the "vehicle-events" topic
        KStream<String, String> stream = builder.stream("vehicle-events");
        // Process each record in the stream by prepending "Processed: " to the value
        stream.mapValues(value -> "Processed: " + value)
        // Sending the processed records to the "processed-vehicle-events" topic
              .to("processed-vehicle-events", Produced.with(Serdes.String(), Serdes.String()));
        return builder;
    }
 // Define a bean for creating and starting a KafkaStreams instance
    @Bean
     KafkaStreams kafkaStreams(StreamsBuilder builder) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "vehicle-events-processor");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();
        return streams;
    }
}

