package net.vehicle.vmsbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.vehicle.vmsbackend.entity.Vehicle;

@Repository
public interface Vehiclerepository extends JpaRepository<Vehicle, Long> {

}
