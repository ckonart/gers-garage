package com.gersgarage.controllers;


import com.gersgarage.models.Vehicle;
import com.gersgarage.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.findAllVehicles();
    }

    @GetMapping("/id/{vehicleId}")
    public List<Vehicle> getVehicleById(@PathVariable Integer vehicleId) {
        return vehicleService.findVehicleById(vehicleId);
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<List<Vehicle>> getVehicleByModel(@PathVariable String model) {
        List<Vehicle> vehicle = vehicleService.findVehicleByModel(model);

        if (vehicle.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        } else {
            return ResponseEntity.ok(vehicle); // 200 OK with the list of clients
        }
    }

    @GetMapping("brand/{brand}")
    public List<Vehicle> getVehicleByBrand(@PathVariable String brand) {
        return vehicleService.findVehicleByBrand(brand);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Integer vehicleId) {
        vehicleService.deleteVehicleById(vehicleId);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<String> updateVehicle(@PathVariable Integer vehicleId, @RequestBody Vehicle updatedVehicle) {
        boolean updated = vehicleService.updateVehicle(vehicleId, updatedVehicle);

        if (updated) {
            return ResponseEntity.ok("Vehicle updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    }
