package net.vehicle.vmsbackend.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.vehicle.vmsbackend.dto.Vehicledto;
import net.vehicle.vmsbackend.event.KafkaEventProducer;
import net.vehicle.vmsbackend.service.Vehicleservice;

//This annotation indicates that this class serves as a REST controller
@RestController
//This annotation maps HTTP requests to handler methods of MVC and REST controllers
@RequestMapping("/api/vehicles")
public class Vehiclecontroller {
	private Vehicleservice vehicleservice;
	private  KafkaEventProducer kafkaEventProducer;
	
	public Vehiclecontroller(Vehicleservice vehicleservice, KafkaEventProducer kafkaEventProducer) {
		this.vehicleservice = vehicleservice;
		this.kafkaEventProducer = kafkaEventProducer;
	}
	// Method to handle HTTP POST requests for creating a new vehicle
	@PostMapping
	public ResponseEntity<Vehicledto> createVehicle(@RequestBody Vehicledto vehicledto){
		Vehicledto savedVehicle = vehicleservice.createVehicle(vehicledto);
		kafkaEventProducer.sendEvent("create", savedVehicle.getId());
		return new ResponseEntity<>(savedVehicle,HttpStatus.CREATED);
	}
	
	 // Method to handle HTTP GET requests for retrieving a vehicle by its ID
	@GetMapping("/{id}")
	public ResponseEntity<Vehicledto> getVehicleById(@PathVariable("id") Long vehicleId){
		Vehicledto vehicledto = vehicleservice.getVehicleById(vehicleId);
		return ResponseEntity.ok(vehicledto);
	}
	// Method to handle HTTP GET requests for retrieving all vehicles
	@GetMapping
	public ResponseEntity<List<Vehicledto>> getAllVehciles(){
		List<Vehicledto> vehicle = vehicleservice.getAllVehicles();
		return ResponseEntity.ok(vehicle);
	}
	// Method to handle HTTP PUT requests for updating a vehicle by its ID
	@PutMapping("/{id}")
	public ResponseEntity<Vehicledto> updateVehicle(@PathVariable("id") Long vehicleId,@RequestBody Vehicledto updatedVehicle){
		    Vehicledto vehicledto = vehicleservice.updateVehicle(vehicleId, updatedVehicle);
		    kafkaEventProducer.sendEvent("update", vehicleId);
		    return ResponseEntity.ok(vehicledto);
	}
	
	// Method to handle HTTP DELETE requests for deleting a vehicle by its ID
		 @DeleteMapping("/{id}")
		    public ResponseEntity<String> deleteVehicle(@PathVariable("id") Long vehicleId) {
		        vehicleservice.deleteVehicle(vehicleId);
		        kafkaEventProducer.sendEvent("delete", vehicleId);
		        return new ResponseEntity<>("Vehicle deleted successfully", HttpStatus.OK);
		 }
}







