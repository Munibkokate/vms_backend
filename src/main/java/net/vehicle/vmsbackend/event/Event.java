package net.vehicle.vmsbackend.event;

import java.time.Instant;

public class Event {

    private String action;
    private Instant timestamp;
    private Long vehicleId;

    // No-argument constructor
    public Event() {
    }

    // All-argument constructor
    public Event(String action, Instant timestamp, Long vehicleId) {
        this.action = action;
        this.timestamp = timestamp;
        this.vehicleId = vehicleId;
    }

    // Getters and Setters
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
