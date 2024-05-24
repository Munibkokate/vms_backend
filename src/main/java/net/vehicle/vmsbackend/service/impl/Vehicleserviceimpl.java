package net.vehicle.vmsbackend.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;
import net.vehicle.vmsbackend.dto.Vehicledto;
import net.vehicle.vmsbackend.entity.Vehicle;
import net.vehicle.vmsbackend.event.Event;
import net.vehicle.vmsbackend.exception.Resourcenotfound;
import net.vehicle.vmsbackend.mapper.VehicleMapper;
import net.vehicle.vmsbackend.repository.Vehiclerepository;
import net.vehicle.vmsbackend.service.EventProducerService;
import net.vehicle.vmsbackend.service.Vehicleservice;

//Annotation the class as a Spring service
@Service
public class Vehicleserviceimpl implements Vehicleservice {
	
	 private final Vehiclerepository vehiclerepository;
	    private final EventProducerService eventProducerService;

	    public Vehicleserviceimpl(Vehiclerepository vehiclerepository, EventProducerService eventProducerService) {
	        this.vehiclerepository = vehiclerepository;
	        this.eventProducerService = eventProducerService;
	    }
	@Override
	public Vehicledto createVehicle(Vehicledto vehicledto) {
		  // Converting dto to entity
		Vehicle vehicle = VehicleMapper.mapToVehicle(vehicledto);
		// Saving the entity to the repository
		Vehicle savedvehicle = vehiclerepository.save(vehicle);
		 // Creating an event for the creation action
		Event event = new Event("create",Instant.now(),savedvehicle.getId());
		 // Sending the event to Kafka
		eventProducerService.sendEvent("vehicle-events", event);
		// Converting the saved entity back to dto and return
		return VehicleMapper.mapToVehicledto(savedvehicle);
		}
	@Override
	public Vehicledto getVehicleById(Long vehicleId) {
		Vehicle vehicle = vehiclerepository.findById(vehicleId)
				.orElseThrow(()-> new Resourcenotfound("Vehicle Does not exist with given id:"+vehicleId));
				return VehicleMapper.mapToVehicledto(vehicle);
	}
	@Override
	public List<Vehicledto> getAllVehicles(){
		List<Vehicle> vehicles = vehiclerepository.findAll();
		return vehicles.stream().map((vehicle) -> VehicleMapper.mapToVehicledto(vehicle))
				.collect(Collectors.toList());
	}
	@Override
	public Vehicledto updateVehicle(Long vehicleId, Vehicledto updatedVehicle) {
		Vehicle vehicle = vehiclerepository.findById(vehicleId).orElseThrow(
				() -> new Resourcenotfound("Vehicle Does not exist with given Id:"+vehicleId)
		);
		vehicle.setReg_no(updatedVehicle.getReg_no());
		vehicle.setModel(updatedVehicle.getModel());
		vehicle.setCompany(updatedVehicle.getCompany());
		vehicle.setPurchase_year(updatedVehicle.getPurchase_year());
		Vehicle updatedVehicleobj=vehiclerepository.save(vehicle);
		 Event event = new Event("update", Instant.now(), updatedVehicleobj.getId());
	     eventProducerService.sendEvent("vehicle-events", event);
		return VehicleMapper.mapToVehicledto(updatedVehicleobj);
	}
	@Override
	public void deleteVehicle(Long vehicleId) {
        Vehicle vehicle = vehiclerepository.findById(vehicleId)
                .orElseThrow(() -> new Resourcenotfound("Vehicle does not exist with given Id: " + vehicleId));
        vehiclerepository.delete(vehicle);
        Event event = new Event("delete", Instant.now(), vehicleId);
        eventProducerService.sendEvent("vehicle-events", event);
	}	
}
