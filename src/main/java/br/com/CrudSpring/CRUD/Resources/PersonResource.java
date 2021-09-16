package br.com.CrudSpring.CRUD.Resources;


import br.com.CrudSpring.CRUD.Model.Person;
import br.com.CrudSpring.CRUD.Repositories.PersonRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Api
@RestController
@RequestMapping(path="/persons")
public class PersonResource {
    private PersonRepository personRepository;

    public PersonResource(PersonRepository personRepository) {
        super();
        this.personRepository = personRepository;
    }

    @ApiOperation("Cadastra pessoas, uma por vez.")
    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        personRepository.save(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @ApiOperation("Consulta pessoas, retornando todas em uma lista.")
    @GetMapping
    public ResponseEntity<List<Person>> getAll(){
        List<Person> persons = new ArrayList<>();
        persons = personRepository.findAll();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @ApiOperation("Consulta uma pessoa pelo seu ID")
    @GetMapping(path="/{id}")
    public ResponseEntity<Optional<Person>> getById(@PathVariable Integer id) {
        Optional<Person> person;
        try {
            person = personRepository.findByid(id);
            return new ResponseEntity<Optional<Person>>(person, HttpStatus.OK);

        } catch (NoSuchElementException er) {
            return new ResponseEntity<Optional<Person>>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Remove uma pessoa através de seu ID")
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Optional<Person>> deleteById(@PathVariable Integer id) {
        try{
            personRepository.deleteById(id);

            return new ResponseEntity<Optional<Person>>(HttpStatus.OK);

        } catch (NoSuchElementException er) {
            return new ResponseEntity<Optional<Person>>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Atualiza os dados de uma pessoa")
    @PutMapping(value="/{id}")
    public ResponseEntity<Person> update(@PathVariable Integer id, @RequestBody Person newPerson){
        return personRepository.findByid(id)
                .map(person -> {
                    person.setName(newPerson.getName());
                    person.setAge(newPerson.getAge());
                    Person personoUpdated = personRepository.save(person);
                    return ResponseEntity.ok().body(personoUpdated);
                }).orElse(ResponseEntity.notFound().build());

    }


}
