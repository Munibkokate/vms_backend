package net.vehicle.vmsbackend.event;

//It contains the schema and getters setters of kafka logging details.
public class Event {
    private String action;
    private long timestamp;
    private long vehicleId;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public long getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}
	
}