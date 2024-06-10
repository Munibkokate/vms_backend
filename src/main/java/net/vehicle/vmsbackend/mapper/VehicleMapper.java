package net.vehicle.vmsbackend.mapper;

import net.vehicle.vmsbackend.dto.Vehicledto;
import net.vehicle.vmsbackend.entity.Vehicle;

public class VehicleMapper {
	
	//Converts a Vehicle entity to a Vehicledto.
	public static Vehicledto mapToVehicledto(Vehicle vehicle){
		return new Vehicledto(
				vehicle.getId(),
				vehicle.getReg_no(),
				vehicle.getreg_date(),
				vehicle.getModel(),
				vehicle.getCompany(),
				vehicle.getPurchase_year()
		);
	}
	// Converts a Vehicledto to a Vehicle entity.
	public static Vehicle mapToVehicle(Vehicledto vehicledto){
		return new Vehicle(
				vehicledto.getId(),
				vehicledto.getReg_no(),
				vehicledto.getreg_date(),
				vehicledto.getModel(),
				vehicledto.getCompany(),
				vehicledto.getPurchase_year()
		);
	}
}