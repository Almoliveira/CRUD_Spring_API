package br.com.CrudSpring.CRUD.Resources;

import br.com.CrudSpring.CRUD.Model.Vehicle;
import br.com.CrudSpring.CRUD.Repositories.VehicleRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Api
@RestController
@RequestMapping(path="/vehicle")
public class VehicleResource {

    private VehicleRepository vehicleRepository;

    public VehicleResource(VehicleRepository vehicleRepository) {
        super();
        this.vehicleRepository = vehicleRepository;
    }

    @ApiOperation("Cadastra um veiculo")
    @PostMapping
    public ResponseEntity<Vehicle> Save (@RequestBody Vehicle vehicle){

        vehicleRepository.save(vehicle);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);

    }

    @ApiOperation("Consulta todos os veiculos")
    @GetMapping
    public ResponseEntity<List<Vehicle>> GetAll(){
        List<Vehicle> list = vehicleRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation("Consulta um veiculo pelo seu ID")
    @GetMapping(path="/{id}")
    public ResponseEntity<Optional<Vehicle>> getByID(@PathVariable Integer id){
        Optional<Vehicle> vehicle;
        try {
            vehicle = vehicleRepository.findByid(id);
            return new ResponseEntity<Optional<Vehicle>>(vehicle, HttpStatus.OK);
        } catch (NoSuchElementException er) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Remove um veiculo pelo seu ID")
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Optional<Vehicle>> RemoveById(@PathVariable Integer id){
        try{
           vehicleRepository.deleteById(id);
           return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException er) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Atualiza os dados de um veiculo")
    @PutMapping(path="/{id}")
    public ResponseEntity<Vehicle> update(@PathVariable Integer id, @RequestBody Vehicle newVehicle) {
        try{
            return vehicleRepository.findByid(id)
                    .map(vehicle -> {
                       vehicle.setModel(newVehicle.getModel());
                       vehicle.setLastKm(newVehicle.getLastKm());
                       vehicle.setLicensePlate(newVehicle.getLicensePlate());
                       vehicle.setMaxPassengers(newVehicle.getMaxPassengers());
                       Vehicle vehicleUpdated = vehicleRepository.save(vehicle);
                       return ResponseEntity.ok().body(vehicleUpdated);

                    }).orElse(ResponseEntity.notFound().build());
        } catch (NoSuchElementException er) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
