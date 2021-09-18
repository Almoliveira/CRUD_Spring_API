package br.com.CrudSpring.CRUD.Repositories;


import br.com.CrudSpring.CRUD.Model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    Optional<Vehicle> findByid(Integer id);
}
