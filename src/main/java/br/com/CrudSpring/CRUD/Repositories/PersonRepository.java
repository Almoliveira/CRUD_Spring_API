package br.com.CrudSpring.CRUD.Repositories;

import br.com.CrudSpring.CRUD.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {


    Optional<Person> findByid(Integer id);
}
