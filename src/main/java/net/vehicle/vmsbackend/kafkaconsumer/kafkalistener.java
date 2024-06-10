package net.vehicle.vmsbackend.kafkaconsumer;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class kafkalistener {

    @KafkaListener(topics = "processed-vehicle-events", groupId = "processedVehicleEventConsumerGroup")
    public void listen(String message) {
        System.out.println("Received processed message: " + message);
    }
}
