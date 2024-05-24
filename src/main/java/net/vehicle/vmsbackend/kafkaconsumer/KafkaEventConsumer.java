package net.vehicle.vmsbackend.kafkaconsumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaEventConsumer {

    public static void main(String[] args) {
        //Kafka bootstrap 
        String bootstrapServers = "localhost:9092";
        //Kafka topic to consume from
        String topic = "vehicle-events";
        // Group ID for the consumer group
        String groupId = "vehicle-event-consumer-group";
        // Kafka consumer properties
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        // Creating Kafka consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList(topic));

        // Start consuming messages
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("Received Event:");
                    System.out.println("Action: " + record.key());
                    System.out.println("Timestamp: " + record.timestamp());
                    System.out.println("Vehicle ID: " + record.value());
                }
            }
        } finally {
            consumer.close();
        }
    }
}
