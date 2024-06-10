package net.vehicle.vmsbackend.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//This class is responsible for producing Kafka events and publishing them to a Kafka topic.
@Component
@Service
public class KafkaEventProducer {
	
	// The name of the Kafka topic to which events will be published.
    private static final String TOPIC = "vehicle-events";
    
    // KafkaTemplate is used to send messages to Kafka topics.
    private final KafkaTemplate<String, String> kafkaTemplate;

 // Constructor for injecting the KafkaTemplate dependency.
    public KafkaEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
     this.kafkaTemplate = kafkaTemplate;
    }
    
 // Method to send an event to the Kafka topic.
    public void sendEvent(String action, Long vehicleId) {
        try {
        	String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        	 // Create the event message as a JSON string.
        	String message = "{\"action\":\"" + action + "\",\"vehicleId\":\"" + vehicleId + "\",\"timestamp\":" + timestamp + "}";
            kafkaTemplate.send(TOPIC, message);
         // Print a success message to the console.
            System.out.println("Event published successfully: " + message);
        } catch (Exception e) {
            System.err.println("Error publishing event: " + e.getMessage());
        }
    }
}
