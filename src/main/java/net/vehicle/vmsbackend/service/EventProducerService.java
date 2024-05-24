package net.vehicle.vmsbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import net.vehicle.vmsbackend.event.Event;

@Service
@RequiredArgsConstructor
public class EventProducerService {
    private KafkaTemplate<String, Object> kafkaTemplate;
    public void sendEvent(String topic, Event event) {
        kafkaTemplate.send(topic, event);
    }
}
