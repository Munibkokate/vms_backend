package net.vehicle.vmsbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import net.vehicle.vmsbackend.dto.Vehicledto;


@Service
public interface Vehicleservice {
	Vehicledto createVehicle(Vehicledto vehicledto);
	Vehicledto getVehicleById(Long vehicleId);
	List<Vehicledto> getAllVehicles();
	Vehicledto updateVehicle(Long vehicleId, Vehicledto updatedVehicle);
	void deleteVehicle(Long vehicleId);
}
