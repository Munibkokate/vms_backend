package net.vehicle.vmsbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import net.vehicle.vmsbackend.entity.Vehicle;


public interface Vehiclerepository extends JpaRepository<Vehicle, Long> {

}
