package net.vehicle.vmsbackend.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import net.vehicle.vmsbackend.dto.Vehicledto;
import net.vehicle.vmsbackend.entity.Vehicle;
import net.vehicle.vmsbackend.exception.Resourcenotfound;
import net.vehicle.vmsbackend.mapper.VehicleMapper;
import net.vehicle.vmsbackend.repository.Vehiclerepository;
import net.vehicle.vmsbackend.service.Vehicleservice;

@Service
public class Vehicleserviceimpl implements Vehicleservice {
    
    private final Vehiclerepository vehiclerepository;

    public Vehicleserviceimpl(Vehiclerepository vehiclerepository) {
        this.vehiclerepository = vehiclerepository;
    }

    @Override
    public Vehicledto createVehicle(Vehicledto vehicledto) {
        try {
            Vehicle vehicle = VehicleMapper.mapToVehicle(vehicledto);
            Vehicle savedVehicle = vehiclerepository.save(vehicle);
            return VehicleMapper.mapToVehicledto(savedVehicle);
        } catch (DataIntegrityViolationException e) {
            throw new Resourcenotfound("Vehicle with registration number " + vehicledto.getReg_no() + " already exists.");
        }
    }

    @Override
    public Vehicledto getVehicleById(Long vehicleId) {
        Vehicle vehicle = vehiclerepository.findById(vehicleId)
                .orElseThrow(() -> new Resourcenotfound("Vehicle does not exist with given id: " + vehicleId));
        return VehicleMapper.mapToVehicledto(vehicle);
    }

    @Override
    public List<Vehicledto> getAllVehicles() {
        List<Vehicle> vehicles = vehiclerepository.findAll();
        return vehicles.stream()
                .map(VehicleMapper::mapToVehicledto)
                .collect(Collectors.toList());
    }

    @Override
    public Vehicledto updateVehicle(Long vehicleId, Vehicledto updatedVehicle) {
        Vehicle vehicle = vehiclerepository.findById(vehicleId)
                .orElseThrow(() -> new Resourcenotfound("Vehicle does not exist with given id: " + vehicleId));
        try {
            vehicle.setReg_no(updatedVehicle.getReg_no());
            vehicle.setModel(updatedVehicle.getModel());
            vehicle.setCompany(updatedVehicle.getCompany());
            vehicle.setPurchase_year(updatedVehicle.getPurchase_year());
            Vehicle updatedVehicleObj = vehiclerepository.save(vehicle);
            return VehicleMapper.mapToVehicledto(updatedVehicleObj);
        } catch (DataIntegrityViolationException e) {
            throw new Resourcenotfound("Vehicle with registration number " + updatedVehicle.getReg_no() + " already exists.");
        }
    }

    @Override
    public void deleteVehicle(Long vehicleId) {
        Vehicle vehicle = vehiclerepository.findById(vehicleId)
                .orElseThrow(() -> new Resourcenotfound("Vehicle does not exist with given id: " + vehicleId));
        vehiclerepository.delete(vehicle);
    }
}
